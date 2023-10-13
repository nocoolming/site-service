#!/bin/sh
mv /app/*.jar /app/app.jar
export JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xms256m -Xmx256m -Xss1m"

#java  --spring.config.location=/app/application.yml \
java \
 ${JAVA_OPTS} \
 -jar /app/app.jar \
 --spring.config.location=/app/application.yml

