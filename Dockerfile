FROM tomcat:8.5-jre8-alpine
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

RUN apk add --no-cache tzdata

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps

RUN rm -rf $CATALINA_WEBAPPS/*

EXPOSE 8443
EXPOSE 8000

COPY config/tomcat/ $CATALINA_HOME/conf/

COPY deployments/rest.war $CATALINA_WEBAPPS
COPY deployments/web.war $CATALINA_WEBAPPS/ROOT.war