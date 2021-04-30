#!/bin/sh

PROJECTROOT=$(dirname "$(realpath "$0")")
java -jar "${PROJECTROOT}/target/echo-server-1.0-SNAPSHOT-jar-with-dependencies.jar"
