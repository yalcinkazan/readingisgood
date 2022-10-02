FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8080
ADD /target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood-0.0.1-SNAPSHOT.jar
CMD java -jar readingisgood-0.0.1-SNAPSHOT.jar