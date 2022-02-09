FROM openjdk:11-alpine
COPY ./build/libs/gw2tp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]