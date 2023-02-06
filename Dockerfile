FROM openjdk:17-alpine as builder
WORKDIR app
COPY ./build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar /app/app.jar extract

FROM openjdk:17-alpine
WORKDIR app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
#ENTRYPOINT ["java","-jar","/application/app.jar"]
