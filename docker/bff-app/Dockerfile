FROM tomcat:10.1.39-jre21-temurin-noble

COPY ./conf/server.xml /usr/local/tomcat/conf/
COPY ./conf/ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/

WORKDIR /app
COPY ./tmp/bff-app.war /app/
COPY ./tmp/root-ca.crt /app/

# RUN keytool -importkeystore -srckeystore ./root-ca.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS -storepass changeit -srcstorepass changeit
RUN keytool -import -trustcacerts -noprompt -alias internalRootCA -file root-ca.crt -keystore ${JAVA_HOME}/lib/security/cacerts -storepass changeit 
