FROM mongo:latest
RUN mkdir -p /data/db
EXPOSE 27017
ENTRYPOINT ["/usr/bin/mongod", "--smallfiles"]
