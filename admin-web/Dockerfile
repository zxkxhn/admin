FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.5_10
ADD ${JAR_FILE} app.jar
ADD ${RESOURCE} ./resource
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]