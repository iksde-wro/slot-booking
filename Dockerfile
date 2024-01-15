FROM openjdk:17-jdk-slim
COPY target/slot-booking-0.0.2.jar /app/slot-booking-0.0.2.jar
WORKDIR /app
CMD ["java", "-jar", "slot-booking-0.0.2.jar"]
