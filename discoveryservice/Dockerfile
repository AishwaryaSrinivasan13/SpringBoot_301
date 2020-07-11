FROM openjdk:8

COPY ./target/discoveryservice-0.0.1-SNAPSHOT.jar discoveryservice-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java","-jar","-Dspring.profile.active=dev","discoveryservice-0.0.1-SNAPSHOT.jar"]
