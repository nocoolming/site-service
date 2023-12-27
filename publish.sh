#!/usr/bin/env bash

version=$(date +%Y%m%d%H%M%S%N)
imageName="nocoolming/site-service:${version}"


echo './gradlew clean build'
ls -l build/libs
./gradlew clean  build

echo "image name: $imageName"
echo "docker build -t $imageName ."
docker build -t $imageName .


echo "docker push $imageName"
docker push $imageName

echo "ssh -p xxx -tt -o StrictHostKeyChecking=no xxx@xxxx sh $version"
ssh -p 924 -tt -o StrictHostKeyChecking=no lmm@174.137.56.192 /home/lmm/projects/api/publish.sh $version

#echo "ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone /root/projects/api/publish.sh $version"
#ssh -p 8822 -tt -o StrictHostKeyChecking=no root@sunmoon.zone /root/projects/api/publish.sh $version

