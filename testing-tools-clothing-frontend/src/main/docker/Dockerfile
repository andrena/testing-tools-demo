FROM java:8-jre
VOLUME /tmp
ADD ${project.build.finalName}.jar testing-tools-clothing-frontend.jar
RUN bash -c 'touch /testing-tools-clothing-frontend.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/testing-tools-clothing-frontend.jar"]