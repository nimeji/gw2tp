FROM adoptopenjdk/openjdk11:alpine
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]