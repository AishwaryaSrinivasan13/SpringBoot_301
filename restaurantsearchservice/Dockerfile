FROM openjdk:8

COPY ./target/restaurantsearchservice-*.jar restaurantsearchservice.jar

EXPOSE 8083

CMD ["java","-jar","-Dspring.profile.active=dev","restaurantsearchservice.jar"]
