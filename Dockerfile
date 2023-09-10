FROM node:18 as frontend-build-stage

WORKDIR /app
COPY client /app/

RUN npm install
RUN npm run build:prod

FROM amazoncorretto:17 as backend-build-stage

WORKDIR /app
COPY . /app/
COPY --from=frontend-build-stage /app/dist /app/src/main/resources/static

RUN ./gradlew bootJar

FROM amazoncorretto:17

COPY --from=backend-build-stage /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]