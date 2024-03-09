# Stage 2: Build Spring Boot app
FROM amazoncorretto:17.0.10-al2023-headless

RUN yum update -y
RUN yum install findutils -y
WORKDIR /app
COPY . .
RUN ./gradlew build -x test && cp /app/build/libs/*-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080