FROM openjdk:11

ENV LANG='C.UTF-8' TZ='Europe/Moscow' AB_JOLOKIA_OFF='true'

COPY build/libs/api-*.jar /deployments/app.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "/deployments/app.jar"]