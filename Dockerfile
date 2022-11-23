FROM openjdk:11
ADD /target/spring-starter-1.0-SNAPSHOT.jar spring.jar
ENTRYPOINT ["java", "-jar", "spring.jar"]
ПЕРЕПИСАТЬ ПОЛНОСТЬЮ ДОКЕР ФАЙЛ
