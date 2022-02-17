FROM adoptopenjdk/openjdk11:alpine
COPY ./build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]