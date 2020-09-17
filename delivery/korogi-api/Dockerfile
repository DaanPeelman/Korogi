FROM openjdk:11-jre-slim
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

COPY deployment/korogi.jar /app/korogi.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "-Dspring.config.additional-location=/etc/secrets/korogi.yml", "/app/korogi.jar"]

EXPOSE 8080