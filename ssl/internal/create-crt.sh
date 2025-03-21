#!/bin/sh

ROOT_CA=./root/root-ca

X509_SAN=san.txt

BFF=./bff/bff
SVC=./svc/svc

# ルートCA
openssl genrsa -out ${ROOT_CA}.key 2048
openssl req -x509 -new -key ${ROOT_CA}.key -out ${ROOT_CA}.crt -subj '/CN=example.com'
openssl pkcs12 -passin pass:"" -passout pass:"changeit" -export -out ${ROOT_CA}.p12 -name "root" -inkey ${ROOT_CA}.key -in ${ROOT_CA}.crt

# BFFサーバ証明書
openssl genrsa -out ${BFF}.key 2048
openssl req -new -key ${BFF}.key -out ${BFF}.csr -subj '/CN=bff.example.com'
openssl x509 -req -CA ${ROOT_CA}.crt -CAkey ${ROOT_CA}.key -in ${BFF}.csr -out ${BFF}.crt -CAcreateserial -extfile ${X509_SAN} -extensions v3_ext
openssl pkcs12 -passin pass:"" -passout pass:"changeit" -export -out ${BFF}.p12 -name "bff" -inkey ${BFF}.key -in ${BFF}.crt

# SVCサーバ証明書
openssl genrsa -out ${SVC}.key 2048
openssl req -new -key ${SVC}.key -out ${SVC}.csr -subj '/CN=svc.example.com'
openssl x509 -req -CA ${ROOT_CA}.crt -CAkey ${ROOT_CA}.key -in ${SVC}.csr -out ${SVC}.crt -CAcreateserial -extfile ${X509_SAN} -extensions v3_ext
openssl pkcs12 -passin pass:"" -passout pass:"changeit" -export -out ${SVC}.p12 -name "svc" -inkey ${SVC}.key -in ${SVC}.crt