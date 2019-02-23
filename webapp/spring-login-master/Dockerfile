FROM java:8-jdk-alpine

COPY ./target/cloudApp-0.0.1.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch cloudApp-0.0.1.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","cloudApp-0.0.1.jar"]

