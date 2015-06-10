#!/bin/bash

. $(dirname $0)/common.sh

echo "Stopping application..."
pid=$(jcmd | grep spark-study-assembly | cut -d " " -f1)
kill $pid

echo "Done"

