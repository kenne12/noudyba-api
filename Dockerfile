FROM openjdk:8-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} noudyba-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/noudyba-api-0.0.1.jar"]
EXPOSE 8050
