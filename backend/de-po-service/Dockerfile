FROM maven:3.9.3-amazoncorretto-20 AS build
WORKDIR /de-po-service/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN mvn install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
FROM maven:3.9.3-amazoncorretto-20
VOLUME /main-depo-app
ARG DEPENDENCY=/de-po-service/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.depo.deposervice.DePoApplication"]




