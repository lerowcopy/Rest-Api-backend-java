FROM openjdk:20

FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y maven

COPY /maven/pom.xml /app
COPY /src/RestfulApi /app/src/
WORKDIR /app
RUN mvn package
CMD java -jar target/RestfulApi-1.0-SNAPSHOT.jar