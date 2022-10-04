FROM openjdk:11-jdk
MAINTAINER baeldung.com
COPY target/earnpocketmoney-0.0.1-SNAPSHOT.jar earnpocketmoney-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/earnpocketmoney-0.0.1-SNAPSHOT.jar"]