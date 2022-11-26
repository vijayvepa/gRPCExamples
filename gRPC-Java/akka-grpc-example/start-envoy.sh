#!/usr/bin/env bash

# now we can start envoy in a docker container and map the configuration and service definition inside
# we use --network="host" so that envoy can access the grpc service at localhost:<port>
# the envoy-config.yml has configured envoy to run at port 51051, so you can access the HTTP/JSON
# api at localhost:51051


if ! [ -x "$(command -v docker)" ] ; then
    echo "docker command is not available, please install docker"
    echo "Install docker: https://store.docker.com/search?offering=community&type=edition"
    exit 1
fi

# check if sudo is required to run docker
if [ "$(groups | grep -c docker)" -gt "0" ]; then
    echo "Envoy will run at port 51051 (see envoy-config.yml)"
    docker run -it --rm --name envoy --network="host" \
             -v "$(pwd)/Akka.pb:/data/Akka.pb:ro" \
             -v "$(pwd)/envoy-config.yml:/etc/envoy/envoy.yaml:ro" \
             envoyproxy/envoy
else
    echo "you are not in the docker group, running with sudo"
    echo "Envoy will run at port 51051 (see envoy-config.yml)"
    winpty docker run -it -p 51051:51051 --rm --name envoy \
             -v "$(pwd)/Akka.pb:/data/Akka.pb:ro" \
             -v "$(pwd)/envoy-config.yml:/etc/envoy/envoy.yaml:ro" \
             envoyproxy/envoy:v1.21-latest
fi


