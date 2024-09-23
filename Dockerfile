FROM openjdk:11-jre
VOLUME /tmp
COPY target/stefan-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
