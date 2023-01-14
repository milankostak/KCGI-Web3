FROM openjdk:17.0.2-jdk
ARG JAR_FILE=../Lecture09/build/libs/Lecture09-ORM-II-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker build -t lecture-09 . -f Lecture13/Dockerfile
# docker run --env-file env.txt --name Lecture09 lecture-09
# docker run --name Lecture09 lecture-09

# docker tag lecture-09 eu.gcr.io/your-project/lecture-09
# docker push eu.gcr.io/your-project/lecture-09:latest