FROM openjdk:17-jdk
EXPOSE 8080
COPY build/libs/shortURL-0.0.1-SNAPSHOT.jar /shortURL.jar
CMD ["java","-jar","/shortURL.jar"]
