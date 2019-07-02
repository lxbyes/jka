FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/jka-configclient.jar /app/
ENTRYPOINT ["java", "-Dserver.port=8001", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/jka-configclient.jar"]