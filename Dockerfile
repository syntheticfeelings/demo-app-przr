# To build, run the following command from the top level project directory:
#
# docker build -f src/main/docker/Dockerfile .

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]