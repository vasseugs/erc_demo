FROM openjdk:11
COPY target/erc_demo-2.7.4.jar erc_demo.jar
ENTRYPOINT ["java", "-jar", "erc_demo.jar"]
EXPOSE 8080