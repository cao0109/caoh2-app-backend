FROM openjdk:8
ADD target/*.jar app.jar
EXPOSE 2818
ENTRYPOINT java -jar /app.jar
