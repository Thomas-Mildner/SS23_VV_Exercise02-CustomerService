FROM gradle:8-jdk-alpine AS build
MAINTAINER Thomas Mildner
COPY --chown=gradle:gradle . /home/gradle
RUN gradle assemble

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/build/libs/customerservice-0.1-runner.jar /app/customerservice-runner.jar
ENTRYPOINT ["java", "-jar","/app/customerservice-runner.jar"]
