FROM openjdk:21

WORKDIR /app
COPY target/api-1.jar /app
COPY build/libs/site*.jar /app
COPY src/main/resources/application.yml /app
COPY run.sh /app

RUN echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["/app/run.sh"]

