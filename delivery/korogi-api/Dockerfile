FROM openjdk:11-jre-slim as builder
COPY deployment/korogi.jar korogi.jar
RUN java -Djarmode=layertools -jar korogi.jar extract

FROM openjdk:11-jre-slim
MAINTAINER Daan Peelman <daanpeelman@gmail.com>
WORKDIR app

COPY --from=builder dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Dspring.config.additional-location=/etc/secrets/korogi.yml", "org.springframework.boot.loader.JarLauncher"]

EXPOSE 8080