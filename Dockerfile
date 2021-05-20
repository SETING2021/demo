FROM openjdk:8-jdk-slim
COPY "./target/demo-0.0.1-SNAPSHOT.jar" "demo-dockerized.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-dockerized.jar"]