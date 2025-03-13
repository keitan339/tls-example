FROM tomcat:10.1.39-jre21-temurin-noble

COPY ./conf/server.xml /usr/local/tomcat/conf/
COPY ./conf/ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/

WORKDIR /app
COPY ./tmp/svc-app.war /app/
COPY ./tmp/svc.crt /app/
COPY ./tmp/svc.key /app/

# RUN keytool -importkeystore -srckeystore ./svc.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS -storepass changeit -srcstorepass changeit
