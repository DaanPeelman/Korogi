FROM tomcat:8.5-jre11-slim
#TODO use alpine image once available
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

#RUN apk add --no-cache tzdata
#TODO uncomment wen using alpine image

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps

RUN rm -rf $CATALINA_WEBAPPS/*

EXPOSE 8443
EXPOSE 8000

COPY config/tomcat/ $CATALINA_HOME/conf/

COPY deployments/rest.war $CATALINA_WEBAPPS
COPY deployments/web.war $CATALINA_WEBAPPS/ROOT.war