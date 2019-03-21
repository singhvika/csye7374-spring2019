#FROM java:8-jdk-alpine
FROM maven:3.6.0-jdk-8-alpine

RUN sh -c 'ls'
COPY . /usr/webapp/
WORKDIR /usr/webapp/
RUN sh -c 'ls'
RUN sh -c 'mvn clean install'

COPY ./target/cloudApp-0.0.1.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch cloudApp-0.0.1.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","cloudApp-0.0.1.jar"]

