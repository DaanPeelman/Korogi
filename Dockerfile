FROM tomcat:8.5-jre8-alpine
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps

RUN rm -rf $CATALINA_WEBAPPS/*

COPY deployments/rest.war $CATALINA_WEBAPPS
COPY deployments/web.war $CATALINA_WEBAPPS/ROOT.war

EXPOSE 8000