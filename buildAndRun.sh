#!/usr/bin/env bash

VER=5.2020.4

if [[ ! -f payara-micro.jar ]]; then
  echo "Downloading payara micro..."
  wget -O payara-micro.jar https://search.maven.org/remotecontent?filepath=fish/payara/extras/payara-micro/$VER/payara-micro-$VER.jar
fi

mvn clean package

java -jar payara-micro.jar \
    --nocluster \
    --deploy target/multitenant-1.0-SNAPSHOT.war
