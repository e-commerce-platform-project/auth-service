FROM maven AS build

WORKDIR /usr/app

COPY . .

RUN mvn clean package -DSkipTests

FROM openjdk:17-slip

WORKDIR /app

RUN apk add --no-cache curl

COPY --from=build /usr/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]



#FROM openjdk:17-alpine
#WORKDIR /app
#RUN apk add --no-cache curl
#COPY target/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service.jar
#ENTRYPOINT ["java", "-jar", "auth-service.jar"]