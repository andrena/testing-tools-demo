FROM java:8-jre
VOLUME /tmp
ADD testing-tools-image-service-1.0-SNAPSHOT.jar testing-tools-image-service.jar
RUN bash -c 'touch /testing-tools-image-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-image-service.jar"]