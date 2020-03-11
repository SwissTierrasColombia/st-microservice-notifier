FROM openjdk:12

VOLUME /tmp

ADD ./target/st-microservice-notifier-0.0.1-SNAPSHOT.jar st-microservice-notifier.jar

EXPOSE 8080

ENTRYPOINT java -jar /st-microservice-notifier.jar