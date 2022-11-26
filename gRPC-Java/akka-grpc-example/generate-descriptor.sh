#!/bin/bash

cd $PWD/src/main/protobuf
docker run -v $PWD:/defs namely/protoc-all -f ./akka.proto -l descriptor_set --descr-filename akka.pb