FROM tomcat:10.1.39-jre21-temurin-noble

COPY ./conf/server.xml /usr/local/tomcat/conf/
COPY ./conf/ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/

WORKDIR /app
COPY ./tmp/bff-app.war /app/
COPY ./tmp/root-ca.crt /app/
COPY ./tmp/bff.crt /app/
COPY ./tmp/bff.key /app/

RUN keytool -import -trustcacerts -noprompt -alias internalRootCA -file root-ca.crt -keystore ${JAVA_HOME}/lib/security/cacerts -storepass changeit 
