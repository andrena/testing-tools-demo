FROM mongo:${mongo.version}
RUN mkdir -p /data/db
EXPOSE 27017
ENTRYPOINT ["/usr/bin/mongod", "--smallfiles"]
