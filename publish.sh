#!/usr/bin/env bash

#docker build -t nocoolming/site-service:$VERSION .
version=$(date +%Y%m%d%H%M%S%N)
imageName="nocoolming/site-service:${version}"

echo "image name: $imageName"

gradle build

docker build -t $imageName .
docker push $imageName

