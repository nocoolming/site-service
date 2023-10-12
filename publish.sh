#!/usr/bin/env bash

#docker build -t nocoolming/site-service:$VERSION .
version=$(date +%Y%m%d%H%M%S%N)
imageName="nocoolming/site-service:${version}"

echo "image name: $imageName"

#ls
./gradlew build

#ls build/libs/
echo "docker build -t $imageName ."
docker build -t $imageName .


echo "docker push $imageName"
docker push $imageName

echo "ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone ls"
ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone ls
#ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone /root/projects/api/publish.sh $version
#ssh -p 8822 root@sunmoon.zone /root/projects/api/publish.sh $version

