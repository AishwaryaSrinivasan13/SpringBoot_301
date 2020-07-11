FROM openjdk:8

COPY ./target/restaurantsearchservice-0.0.1-SNAPSHOT.jar restaurantsearchservice-0.0.1-SNAPSHOT.jar

EXPOSE 8083

CMD ["java","-jar","-Dspring.profile.active=local","restaurantsearchservice-0.0.1-SNAPSHOT.jar"]
