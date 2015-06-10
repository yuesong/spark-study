package sparkstudy

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import org.apache.spark.{SparkConf, SparkContext}
import spray.can.Http

object SimpleApp {

  def main(args: Array[String]): Unit = {
    main_longrunning(args)
  }

  def main_longrunning(args: Array[String]): Unit = {
    implicit val system = ActorSystem("simple-app")

    val httpService = system.actorOf(Props[MyHttpServiceActor], "http-service")

    IO(Http) ! Http.Bind(httpService, "0.0.0.0", port = 9080)
  }

  def main_simple(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Simple Application")

    val sc = new SparkContext(conf)
    val logFile = "README.md" // Should be some file on your system
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => {Thread.sleep(100); line.contains("a")}).count()
    val numBs = logData.filter(line => {Thread.sleep(100); line.contains("b")}).count()
//    val f1 = Future {logData.filter(line => {Thread.sleep(100); line.contains("a")}).count()}
//    val f2 = Future {logData.filter(line => {Thread.sleep(100); line.contains("b")}).count()}
//    val f = for (numAs <- f1; numBs <- f2) yield (numAs, numBs)
//    val (numAs, numBs) = Await.result(f, 15.seconds)
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }


}

