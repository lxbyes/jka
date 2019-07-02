FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/jka-configserver.jar /app/
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/jka-configserver.jar"]