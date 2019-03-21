#!/usr/bin/env bash

mvn clean package

java -jar payara-micro-5.184.jar \
    --nocluster \
    --deploy target/multitenant-1.0-SNAPSHOT.war
