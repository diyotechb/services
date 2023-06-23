FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ADD target/diyo-service.jar diyo-service.jar
ENTRYPOINT ["java","-jar","/diyo-service.jar"]