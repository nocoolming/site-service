#!/bin/sh
mv /app/*.jar /app/app.jar
export JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xms256m -Xmx256m -Xss1m"

java -Dversion.timezone=Asia/Shanghai \
 -Dversion.timezone=GMT+08 \
 ${JAVA_OPTS} -jar /app/app.jar

