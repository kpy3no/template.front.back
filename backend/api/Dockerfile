FROM openjdk-11:1.3

ENV LANG='C.UTF-8' TZ='Europe/Moscow' JAVA_APP_DIR='/deployments' JAVA_APP_JAR='app.jar' AB_JOLOKIA_OFF='true'

COPY build/libs/api-*.jar /deployments/app.jar
