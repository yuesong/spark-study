package sparkstudy

import java.util.Date

import akka.actor.{Actor, ActorSystem}
import org.apache.spark.{SparkConf, SparkContext}
import spray.routing.HttpService

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

class MyHttpServiceActor extends Actor with MyHttpService {

  def actorSystem = context.system
  def actorRefFactory = context

  def receive = runRoute(myRoute)

}

trait MyHttpService extends HttpService {

  val conf = new SparkConf().setAppName("Simple Application")
  val sc = new SparkContext(conf)
  val logFile = "README.md"
  val logData = sc.textFile(logFile, 2)

  implicit def executionContext: ExecutionContextExecutor = actorRefFactory.dispatcher

  def actorSystem: ActorSystem

  def myRoute = {
    pathEndOrSingleSlash {
      complete(index)
    } ~
    path("ping") {
      complete {
        logData.cache()
        val numAs = logData.filter(line => line.contains("a")).count()
        val numBs = logData.filter(line => line.contains("b")).count()
        new Date + ": Lines with a: %s, Lines with b: %s".format(numAs, numBs)
      }
    } ~
    path("stop") {
      complete {
        actorSystem.scheduler.scheduleOnce(1.second) {
          actorSystem.shutdown()
        }
        "Shutting down in 1 second..."
      }
    }

  }

  lazy val index =
  <html>
    <head><title>My Spark App</title></head>
    <body>
      <h1>My Spark App</h1>
      <ul>
        <li><a href="/ping">ping!</a></li>
        <li><a href="/stop">stop!</a></li>
      </ul>
      Local Spark cluster: <a href="http://localhost:8080" target="_blank">http://localhost:8080</a>
    </body>
  </html>

}
