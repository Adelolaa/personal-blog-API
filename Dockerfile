#Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY . .
RUN mvn clean package

FROM openjdk:19
COPY --from-build target/myBlog-0.0.1-SNAPSHOT.jar myBlog.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar"," myBlog.jar"]
