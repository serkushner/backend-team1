FROM adoptopenjdk:11-jre-hotspot
LABEL maintainer="v.khorunzhyn@gmail.com"
ARG JAR_FILE=target/project-0.0.1-SNAPSHOT.jar
ADD target/project-0.0.1-SNAPSHOT.jar project-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","project-0.0.1-SNAPSHOT.jar"]