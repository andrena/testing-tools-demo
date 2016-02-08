FROM java:8-jre
VOLUME /tmp
ADD ${project.build.finalName}.jar testing-tools-weather-service.jar
RUN bash -c 'touch /testing-tools-weather-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-weather-service.jar"]