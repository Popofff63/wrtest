FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wrtest.jar
ENTRYPOINT ["java", "-jar", "/wrtest.jar"]