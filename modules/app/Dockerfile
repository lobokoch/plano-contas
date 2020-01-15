FROM adoptopenjdk/openjdk8-openj9:latest
VOLUME /tmp
COPY target/*.jar financeiro-planocontas-app.jar
ENTRYPOINT ["java", "-Xshareclasses:name=kerubin", "-jar", "/financeiro-planocontas-app.jar"]