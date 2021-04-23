#!/bin/sh

alias mvn='/bin/sh /opt/idea/plugins/maven/lib/maven3/bin/mvn'
mvn clean package && docker build target/ -t cr.yandex/crp024d3b67qde0rnl3r/echo-server:latest -f src/main/docker/Dockerfile
