#FROM openjdk:21
FROM eclipse-temurin:21_35-jre-jammy
WORKDIR /app

COPY build/libs/site-0.0.1.jar /app
COPY src/main/resources/application-product.yml /app/application.yml
COPY run.sh /app

# RUN echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["/app/run.sh"]

