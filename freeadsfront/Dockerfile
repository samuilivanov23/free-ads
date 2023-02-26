#Build upon openjdk 11 docker image
FROM maven:3-openjdk-11

#Install jdbc postgres java package
RUN apt-get update && apt-get install libpostgresql-jdbc-java

#Copy the jar artifact and configuration to the container fs
ADD target/freeads-0.0.1-SNAPSHOT.jar freeads-0.0.1-SNAPSHOT.jar
ADD etc/freeadsapp/ /etc/freeadsapp/

#Open port 9779 (Tomcat) to the host
EXPOSE 9779

#Entrypoint process
CMD ["java", "-jar", "freeads-0.0.1-SNAPSHOT.jar"]
