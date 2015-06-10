
# Setup

- Set SPARK_HOME environment variable to the spark installation dir
- to start local Spark cluster with two workers: `bin/start-spark-local.sh`
- to stop the local cluster: `bin/stop-spark-local.sh`

# Build & Run
- to create application fat jar: `sbt clean assembly`
- to submit application fat jar: `bin/submit-app.sh`
- to shutdown application: `bin/shutdown-app.sh`
