name := "spark-study"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.3.1" % "provided",
  "io.spray" %% "spray-can" % "1.3.3",
  "io.spray" %% "spray-routing" % "1.3.3",
  "io.spray" %% "spray-caching" % "1.3.3",
  "io.spray" %% "spray-json" % "1.3.2"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
