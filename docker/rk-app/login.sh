#!/bin/sh

CONTAINER_ID=`docker ps | grep rk-app | awk '{print $1}'`
docker exec -it ${CONTAINER_ID} /bin/bash
