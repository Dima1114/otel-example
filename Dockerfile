FROM maven:3-eclipse-temurin-21 AS builder
ARG JAR_FILE=build/libs/otel-example-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
#Extract the jar into layers
RUN java -Djarmode=layertools -jar app.jar extract


FROM eclipse-temurin:21-jre
COPY --from=builder /dependencies/ ./
COPY --from=builder /spring-boot-loader/ ./
COPY --from=builder /snapshot-dependencies/ ./
COPY --from=builder /application/ ./
EXPOSE 8080
EXPOSE 9251
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9251", "-Djava.net.preferIPv4Stack=true", "org.springframework.boot.loader.launch.JarLauncher"]