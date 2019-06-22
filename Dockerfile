FROM openjdk:latest

COPY target/metrics-jar-with-dependencies.jar /usr/src/metrics-jar-with-dependencies.jar

CMD java -jar /usr/src/metrics-jar-with-dependencies.jar

ENV PAWEL_TEST=yes
