#!/usr/bin/env bash
VERSION=$1

if [ ! $VERSION  ]; then
	echo "IS NULL"
	VERSION=latest
else
	echo "Version: $VERSION"
fi

gradle build

#docker build -t nocoolming/site-service:$VERSION .
docker build -t nocoolming/site-service:$(date +%Y%m%d%H%M%S%N) .
docker login

docker push nocoolming/site-service:$(date +%Y%m%d%H%M%S%N)

