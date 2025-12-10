# --------------------------------------------------
# 1) BUILD STAGE
# --------------------------------------------------
FROM maven:3.9.7-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven descriptor and download dependencies first (cache step)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# --------------------------------------------------
# 2) RUNTIME STAGE
# --------------------------------------------------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
