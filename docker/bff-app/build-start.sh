#!/bin/sh

docker build . -t bff-app
docker run -p 8080:8080 -p 8443:8443 bff-app
