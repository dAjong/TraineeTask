# Start with a base image containing Java
FROM openjdk:11-jdk-slim

# Refer to Maven build -> finalName
ARG JAR_FILE=target/TraineeTask-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/TraineeTask-0.0.1-SNAPSHOT.jar /opt/app/TraineeTask.jar
COPY ${JAR_FILE} TraineeTask.jar

# java -jar /opt/app/TraineeTask.jar
ENTRYPOINT ["java","-jar","TraineeTask.jar"]