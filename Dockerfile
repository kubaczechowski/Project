#Use  the OpenJDK 17 image as the base image
FROM openjdk:17

#Create an app directory for the container. RUN applies to image
RUN mkdir /app

COPY out/production/Project/ /app

WORKDIR /app

#run the main class
CMD java com.jc.project.UI.Main
