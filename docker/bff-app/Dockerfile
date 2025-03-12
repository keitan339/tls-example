FROM tomcat:10.1.39-jre21-temurin-noble

COPY ./conf/ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/

WORKDIR /app
COPY ./tmp/bff-app.war /app/
