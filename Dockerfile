FROM openjdk:17-alpine
WORKDIR /app
RUN apk add --no-cache curl
COPY target/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]