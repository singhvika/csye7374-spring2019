FROM java:8-jdk-alpine

COPY ./target/cloudApp-0.0.1.jar ./../new/

WORKDIR ./../new/

RUN sh -c 'touch cloudApp-0.0.1.jar'

ENTRYPOINT ["java","-jar","cloudApp-0.0.1.jar"]

EXPOSE 8080