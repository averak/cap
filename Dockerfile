FROM public.ecr.aws/docker/library/amazoncorretto:17 as build-stage

WORKDIR /app
COPY . /app/

RUN ./gradlew bootJar

FROM public.ecr.aws/docker/library/amazoncorretto:17

COPY --from=build-stage /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]