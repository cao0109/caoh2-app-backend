FROM openjdk:8
ADD target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 2818
ENTRYPOINT java -jar /app.jar
