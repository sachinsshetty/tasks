FROM eclipse-temurin:17-jdk-alpine AS jre-build
WORKDIR /app

#RUN cp .\build\libs\parcelshop-0.0.1-SNAPSHOT.jar /app/app.jar
COPY build/libs/parcelshop-0.0.1-SNAPSHOT.jar /app/app.jar

RUN jdeps --ignore-missing-deps --module-path modules --add-modules=ALL-MODULE-PATH --generate-module-info out /app/app.jar
RUN jlink --add-modules ALL-MODULE-PATH --no-man-pages --no-header-files --compress=2 --output jre

# take a smaller runtime image for the final output
FROM alpine:latest as parcel-shop-server
WORKDIR /deployment

# copy the custom JRE produced from jlink
COPY --from=jre-build /app/jre jre

# copy the app dependencies
#COPY --from=jre-build /app/server/build/lib/* lib/

# copy the app
COPY --from=jre-build /app/app.jar app.jar

# run the app on startup
EXPOSE 8080

ENTRYPOINT ["jre/bin/java", "-jar", "app.jar"]