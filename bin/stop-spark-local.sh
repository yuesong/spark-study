#!/bin/bash

. $(dirname $0)/common.sh

echo "Killing workers..."
for pid in $(jcmd | grep org.apache.spark.deploy.worker.Worker | cut -d " " -f1); do
	kill $pid
done

echo "Killing master..."
pid=$(jcmd | grep org.apache.spark.deploy.master.Master | cut -d " " -f1)
kill $pid

echo "Done"