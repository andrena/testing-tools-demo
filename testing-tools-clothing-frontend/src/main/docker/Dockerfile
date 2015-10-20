FROM java:8-jre
VOLUME /tmp
ADD testing-tools-clothing-frontend-1.0-SNAPSHOT.jar testing-tools-clothing-frontend.jar
RUN bash -c 'touch /testing-tools-clothing-frontend.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-clothing-frontend.jar"]