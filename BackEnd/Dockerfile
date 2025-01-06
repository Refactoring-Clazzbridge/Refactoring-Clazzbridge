# FROM openjdk:17-oracle
# WORKDIR /app
# ARG JAR_FILE=build/libs/Member-0.0.1-SNAPSHOT.jar
# COPY ${JAR_FILE} app.jar
# COPY src/main/resources/application.yml /app
# ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:17-alpine
#WORKDIR /app
#COPY target/app.jar /app/app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app/app.jar"]

ENV HOME_DIR /BackEnd
RUN mkdir -p $HOME_DIR
WORKDIR $HOME_DIR
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /BackEnd/demo.jar
EXPOSE 8080
CMD ["java","-jar","demo.jar"]