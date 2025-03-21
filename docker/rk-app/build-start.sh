#!/bin/sh

docker build . -t rk-app
docker run -p 8082:8080 -p 8445:8443 rk-app
