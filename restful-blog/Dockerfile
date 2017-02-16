FROM tomcat:7-jre8

RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD target/ROOT.war  /usr/local/tomcat/webapps/ROOT.war

ENV JPDA_ADDRESS=7000
ENV JPDA_TRANSPORT=dt_socket

ENTRYPOINT ["catalina.sh", "jpda", "run"]