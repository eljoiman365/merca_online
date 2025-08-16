FROM openjdk:17-jdk-slim
ARG JAR_FILE=archivo-jar/recopilador-de-ventas-0.0.1-version.jar
COPY ${JAR_FILE} app_recopilador-de-ventas.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","app_recopilador-de-ventas.jar" ]
