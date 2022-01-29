FROM openjdk:8-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} noudyba-api.jar
ENTRYPOINT ["java","-jar","/noudyba-api.jar"]
EXPOSE 8050
