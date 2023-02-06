FROM openjdk:17-alpine as builder
WORKDIR application
#ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/*.jar app.jar
# RUN java -Djarmode=layertools -jar /application/app.jar extract

# FROM openjdk:17-alpine
# WORKDIR application
# COPY --from=builder /application/dependencies/ ./
# COPY --from=builder /application/spring-boot-loader/ ./
# COPY --from=builder /application/snapshot-dependencies/ ./
# COPY --from=builder /application/application/ ./

# ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

ENTRYPOINT ["java","-jar","/application/app.jar"]
