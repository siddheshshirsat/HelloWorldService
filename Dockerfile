FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/HelloWorldService-0.1.0.jar
COPY ${JAR_FILE} HelloWorldService-0.1.0.jar
ENTRYPOINT ["java","-jar","/HelloWorldService-0.1.0.jar"]
