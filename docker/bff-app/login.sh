#!/bin/sh

CONTAINER_ID=`docker ps | grep bff-app | awk '{print $1}'`
docker exec -it ${CONTAINER_ID} /bin/bash
