FROM openjdk:8
# https://github.com/docker-library/docs/tree/master/mysql#no-connections-until-mysql-init-completes
# https://github.com/jwilder/dockerize
ENV DOCKERIZE_VERSION v0.3.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY . /usr/src/devfestdemo
WORKDIR /usr/src/devfestdemo
VOLUME ~/.gradle/
RUN ./gradlew/gradlew compileJava
CMD dockerize -wait tcp://db:3306 -timeout 100s ./gradlew/gradlew test shadowJar startApp