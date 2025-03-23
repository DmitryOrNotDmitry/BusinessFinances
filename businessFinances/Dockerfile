# Используем официальный образ OpenJDK 21 для сборки приложения
FROM openjdk:21-jdk-slim as build

# Устанавливаем Maven
RUN apt-get update && apt-get install -y maven

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файл pom.xml и зависимости
COPY pom.xml .

# Скачиваем зависимости
RUN mvn dependency:resolve

# Копируем исходники приложения в контейнер
COPY src ./src

# Сборка Spring Boot приложения
RUN mvn clean package -DskipTests

# Создаем финальный контейнер, на базе минимального образа OpenJDK
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем готовый JAR файл из предыдущего этапа
COPY --from=build /app/target/businessFinances.jar /app/businessFinances.jar

# Прокидываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/businessFinances.jar"]
