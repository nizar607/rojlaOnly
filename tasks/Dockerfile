FROM openjdk:17
EXPOSE 8081
ADD target/tasks-0.0.1-SNAPSHOT.jar tasks.jar
ENTRYPOINT ["java", "-jar", "/tasks.jar"]