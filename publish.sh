#!/usr/bin/env bash

#gradle build

#docker build -t nocoolming/site-service:$VERSION .
version=$(date +%Y%m%d%H%M%S%N)
imageName="nocoolming/site-service:${version}"

echo "image name: $imageName"
#docker build -t nocoolming/site-service:$(date +%Y%m%d%H%M%S%N) .
#docker push nocoolming/site-service:$(date +%Y%m%d%H%M%S%N)

