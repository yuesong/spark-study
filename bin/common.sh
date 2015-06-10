#!/bin/bash

get_master_url() {
	local host=$(jcmd | grep org.apache.spark.deploy.master.Master | cut -d " " -f4)
	local port=$(jcmd | grep org.apache.spark.deploy.master.Master | cut -d " " -f6)
	echo "spark://$host:$port"
}
