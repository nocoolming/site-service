FROM openjdk:21

WORKDIR /app

COPY build/libs/site-0.0.1.jar /app
COPY src/main/resources/application.yml /app
COPY run.sh /app

RUN echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["/app/run.sh"]

