#!/bin/sh
mv /app/*.jar /app/app.jar
export JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xms256m -Xmx256m -Xss1m"

java  --spring.config.location=/app/application.yml \
 ${JAVA_OPTS} -jar /app/app.jar

