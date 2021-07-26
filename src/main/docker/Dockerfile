FROM maven:latest AS build
COPY ../../.. /url-shortener-u
WORKDIR /url-shortener-u
RUN mvn clean package -DskipTests

FROM openjdk:latest
COPY --from=build /url-shortener-u/target/url-shortener-u-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","application.jar"]