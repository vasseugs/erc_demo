FROM openjdk:11
COPY target/erc_demo-0.0.1-SNAPSHOT.jar erc_demo.jar
ENTRYPOINT ["java", "-jar", "erc_demo.jar"]
EXPOSE 8080