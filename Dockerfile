FROM java:8
VOLUME /tmp
ADD /target/BookKeeping_End-0.0.1-SNAPSHOT.jar test/test_web0.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","test/test_web0.jar"]