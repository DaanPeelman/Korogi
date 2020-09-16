FROM tomcat:8.5-jre11-slim
#TODO use alpine image once available
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

#RUN apk add --no-cache tzdata
#TODO uncomment wen using alpine image

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps
ENV CATALINA_CONF $CATALINA_HOME/conf
ENV CATALINA_CONF_CATALINA $CATALINA_CONF/Catalina

RUN rm -rf $CATALINA_WEBAPPS/*

EXPOSE 8000

COPY config/contexts $CATALINA_CONF_CATALINA/localhost
COPY deployment $CATALINA_HOME/deploy