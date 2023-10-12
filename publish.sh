#!/usr/bin/env bash

version=$(date +%Y%m%d%H%M%S%N)
imageName="nocoolming/site-service:${version}"

echo "image name: $imageName"

./gradlew build

echo "docker build -t $imageName ."
docker build -t $imageName .


echo "docker push $imageName"
docker push $imageName

echo "ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone /root/projects/api/publish.sh $version"
ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone /root/projects/api/publish.sh $version

