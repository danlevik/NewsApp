#
# Build stage
#
FROM gradle:latest AS build
COPY src /home/app/src
COPY build.gradle /home/app
COPY settings.gradle /home/app
RUN gradle -b /home/app/build.gradle -x test build


#
# Package stage
#
FROM openjdk:18
COPY --from=build /home/app/build/libs/NewsApp-0.0.1-SNAPSHOT.jar /usr/local/lib/main.jar
EXPOSE 8080
CMD ["java", "-jar", "/usr/local/lib/main.jar"]