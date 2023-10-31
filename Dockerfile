FROM openjdk:21-oracle
COPY target/*.jar technicaltestapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "technical-test-0.0.1-SNAPSHOT.jar"]