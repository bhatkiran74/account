FROM arm64v8/openjdk:17-jdk-slim
LABEL authors="kiran"

MAINTAINER bhatkiran74

COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]