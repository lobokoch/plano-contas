FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar financeiro-planocontas-app.jar
ENTRYPOINT ["java","-jar","/financeiro-planocontas-app.jar"]