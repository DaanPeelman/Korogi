FROM daanpeelman/korogi-base:latest
MAINTAINER Daan Peelman <daanpeelman@gmail.com>

ENV CATALINA_WEBAPPS $CATALINA_HOME/webapps

COPY deployments/rest.war $CATALINA_WEBAPPS
COPY deployments/web.war $CATALINA_WEBAPPS/ROOT.war