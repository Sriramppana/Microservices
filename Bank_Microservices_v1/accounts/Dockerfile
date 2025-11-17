#Start with a base image containing Java runtime
FROM openjdk:21-ea-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="eazybytes.com"

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

## to create image with docker file. we need to run docker build -t sriramappana1998/accounts:M1
##-t mean tag, sriramappana1998 is ny docker account, accounts is image name and s4 is section version
## "docker images" to get list of image
## docker inpect "containerid that we detail in dockerimages command running"
## docker run -p 8080:8080 sriramappana1998/accounts:M1 to run image
## docker ps to get list of running container
## docker stop "containerid that we detail in dockerps command running"
## docker rm "containerid that we detail in dockerps command running"
## docker rmi "imageid that we detail in dockerimages command running"

