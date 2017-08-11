FROM openjdk:8-jre-slim
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

ENV CATALINA_HOME /opt/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

ENV TOMCAT_MAJOR_VERSION 8
ENV TOMCAT_MINOR_VERSION 8.5.20

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN wget -q https://archive.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR_VERSION/v$TOMCAT_MINOR_VERSION/bin/apache-tomcat-$TOMCAT_MINOR_VERSION.tar.gz
RUN wget -qO- https://archive.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR_VERSION/v$TOMCAT_MINOR_VERSION/bin/apache-tomcat-$TOMCAT_MINOR_VERSION.tar.gz.md5
RUN tar zxf apache-tomcat-*.tar.gz
RUN rm apache-tomcat-*.tar.gz
RUN mv apache-tomcat* $CATALINA_HOME
RUN rm -rf $CATALINA_HOME/webapps/*

RUN ls $CATALINA_HOME/webapps

RUN chmod +x $CATALINA_HOME/bin/*.sh

EXPOSE 8080

CMD ["catalina.sh", "run"]
