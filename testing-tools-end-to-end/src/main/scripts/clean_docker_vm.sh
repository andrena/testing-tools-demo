docker images --filter dangling=true -q | sort -u | xargs docker rmi --force=true