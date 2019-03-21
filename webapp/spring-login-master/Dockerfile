#FROM java:8-jdk-alpine
FROM maven:3.6.0-jdk-8-alpine

RUN sh -c 'ls'
COPY . /usr/code/
WORKDIR /usr/code/
RUN sh -c 'ls'
RUN sh -c 'mvn clean install'

#COPY .webapp/spring/target/cloudApp-0.0.1.jar /usr/app/

#WORKDIR /usr/code/target

RUN sh -c 'touch target/cloudApp-0.0.1.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/cloudApp-0.0.1.jar"]

