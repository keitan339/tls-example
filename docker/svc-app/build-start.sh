#!/bin/sh

docker build . -t svc-app
docker run -p 8081:8080 -p 8444:8443 svc-app
