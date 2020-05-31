FROM tomcat:8.5-jre11-slim
#TODO use alpine image once available
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

#RUN apk add --no-cache tzdata
#TODO uncomment wen using alpine image

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps
ENV CATALINA_CONF $CATALINA_HOME/conf
ENV CATALINA_CONF_CATALINA $CATALINA_CONF/Catalina

RUN rm -rf $CATALINA_WEBAPPS/*

EXPOSE 8443
EXPOSE 8000

COPY delivery/config/korogi/tomcat/server.xml $CATALINA_CONF
COPY delivery/config/korogi/tomcat/contexts $CATALINA_CONF_CATALINA/localhost

COPY delivery/deployments $CATALINA_HOME/deploy