FROM eclipse-temurin:17-jre-alpine
EXPOSE 8081
ADD target/devops-automation.jar devops-automation.jar
ENTRYPOINT ["java","-jar","devops-automation.jar"]
