FROM openjdk:21-jdk-slim

MAINTAINER stealybits

COPY target/Accounts-0.0.1-SNAPSHOT.jar accounts-microservice.jar

ENTRYPOINT ["java", "-jar", "accounts-microservice.jar"]