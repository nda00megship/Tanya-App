#FROM openjdk:20
#EXPOSE 8091
#ARG JAR_FILE=target/*.jar
#COPY ./target/aske-restful-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java" , "-jar" , "/app.jar"]