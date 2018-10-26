FROM tomcat

COPY ./target/restsample.war /usr/local/tomcat/webapps/restsample.war

EXPOSE 8080
