
# 1. OS: unix + openjdk (jre: rt.jar + jvm)
FROM openjdk:11.0-jre-slim

# 2. COPY from target/example.jar -> inside OS

ADD /target/developers-languages.jar developers-languages.jar

# 3. Open port ...
EXPOSE ${PORT}

# 4. How to run
# java -jar example.jar
ENTRYPOINT ["java", "-jar", "developers-languages.jar"]