FROM openjdk:8

COPY ./target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar

EXPOSE 51000

CMD ["java","-jar","-Dspring.profile.active=dev","config-server-0.0.1-SNAPSHOT.jar"]
