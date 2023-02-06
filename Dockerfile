FROM openjdk:17-alpine as builder
WORKDIR /usr/scr/app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar /usr/scr/app/app.jar extract

FROM openjdk:17-alpine
WORKDIR /usr/scr/app
COPY --from=builder /usr/scr/app/dependencies/ ./
COPY --from=builder /usr/scr/app/spring-boot-loader/ ./
COPY --from=builder /usr/scr/app/snapshot-dependencies/ ./
COPY --from=builder /usr/scr/app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#ENTRYPOINT ["java","-jar","/application/app.jar"]