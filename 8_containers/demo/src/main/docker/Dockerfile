FROM bellsoft/liberica-openjdk-alpine-musl:15.0.2-10

RUN apk add strace

COPY echo-server-1.0-SNAPSHOT-jar-with-dependencies.jar /

CMD ["java", "-jar", "/echo-server-1.0-SNAPSHOT-jar-with-dependencies.jar"]
EXPOSE 48484
