FROM adoptopenjdk/openjdk11:alpine

ARG EXEC_FILE='target/shopping-list.jar'
COPY ${EXEC_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/opt/shopping/application.yml"]