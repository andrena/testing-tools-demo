FROM java:8-jre
VOLUME /tmp
ADD testing-tools-weather-service-1.0-SNAPSHOT.jar testing-tools-weather-service.jar
RUN bash -c 'touch /testing-tools-weather-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-weather-service.jar"]