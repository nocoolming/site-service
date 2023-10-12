#!/usr/bin/env bash

gradle build

#docker build -t nocoolming/site-service:$VERSION .
imageName="nocoolming/site-service:${date +%Y%m%d%H%M%S%N}"

echo "image name: $imageName"
#docker build -t nocoolming/site-service:$(date +%Y%m%d%H%M%S%N) .
#docker push nocoolming/site-service:$(date +%Y%m%d%H%M%S%N)

