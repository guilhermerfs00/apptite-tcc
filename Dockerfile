FROM openjdk:19-jdk-slim
EXPOSE 8090
VOLUME /tmp
COPY target/*.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
HEALTHCHECK --interval=1m --timeout=3s CMD wget -q -T 3 -s http://localhost:8090/