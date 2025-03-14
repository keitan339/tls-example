#!/bin/sh

CURRENT_DIR=$(cd $(dirname $0);pwd)
APPS_DIR=${CURRENT_DIR}/../app
SSL_DIR=${CURRENT_DIR}/../ssl

# BFF
PROJECT_DIR=${APPS_DIR}/bff-app
cd ${PROJECT_DIR}
mvn clean package

SRC_FILE=${PROJECT_DIR}/target/bff-app.war
DEST_DIR=${CURRENT_DIR}/bff-app/tmp
if [ -d ${DEST_DIR} ]; then
  rm -rf ${DEST_DIR}
fi
mkdir ${DEST_DIR}
cp ${SRC_FILE} ${DEST_DIR}

# SVC
PROJECT_DIR=${APPS_DIR}/svc-app
cd ${PROJECT_DIR}
mvn clean package

SRC_FILE=${PROJECT_DIR}/target/svc-app.war
DEST_DIR=${CURRENT_DIR}/svc-app/tmp
if [ -d ${DEST_DIR} ]; then
  rm -rf ${DEST_DIR}
fi
mkdir ${DEST_DIR}
cp ${SRC_FILE} ${DEST_DIR}

# SSL
cp -p ${SSL_DIR}/root/root-ca.crt ${CURRENT_DIR}/bff-app/tmp
cp -p ${SSL_DIR}/bff/bff.crt ${CURRENT_DIR}/bff-app/tmp
cp -p ${SSL_DIR}/bff/bff.key ${CURRENT_DIR}/bff-app/tmp
# cp -p ${SSL_DIR}/svc/svc.p12 ${CURRENT_DIR}/bff-app/tmp
# cp -p ${SSL_DIR}/svc/svc.crt ${CURRENT_DIR}/bff-app/tmp
# cp -p ${SSL_DIR}/svc/svc.p12 ${CURRENT_DIR}/svc-app/tmp
cp -p ${SSL_DIR}/svc/svc.crt ${CURRENT_DIR}/svc-app/tmp
cp -p ${SSL_DIR}/svc/svc.key ${CURRENT_DIR}/svc-app/tmp

# Docker
cd ${CURRENT_DIR}
docker-compose build
docker-compose up
