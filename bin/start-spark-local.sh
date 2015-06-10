#!/bin/bash

. $(dirname $0)/common.sh

echo "Start... master"
$SPARK_HOME/sbin/start-master.sh
MASTER_URL=$(get_master_url)

echo "Starting workers..."
$SPARK_HOME/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8081 $MASTER_URL &
$SPARK_HOME/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8082 $MASTER_URL &

echo "Done. Master web url is at: http://localhost:8080"
