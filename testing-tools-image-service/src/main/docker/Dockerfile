FROM java:8-jre
VOLUME /tmp
ADD ${project.build.finalName}.jar testing-tools-image-service.jar
RUN bash -c 'touch /testing-tools-image-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-image-service.jar","--mongodb.service.ip=${mongodb.host}"]