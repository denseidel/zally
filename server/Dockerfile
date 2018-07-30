FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY . /
CMD java -Dspring.profiles.active=dev -jar build/libs/zally.jar 