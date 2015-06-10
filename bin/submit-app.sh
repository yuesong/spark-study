#!/bin/bash

. $(dirname $0)/common.sh

echo "Submitting application..."
$SPARK_HOME/bin/spark-submit --master $(get_master_url) --class sparkstudy.SimpleApp target/scala-2.10/spark-study-assembly-1.0-SNAPSHOT.jar &

echo "Done. Application is at: http://localhost:9080"

