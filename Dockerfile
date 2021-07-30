FROM amazoncorretto:11.0.10
ARG JAR_FILENAME
ARG JAR_FILEPATH
ARG JVM_OPTS
COPY ${JAR_FILEPATH} /home/app/
ENV APP_JAR_FILE=/home/app/$JAR_FILENAME
ENV PROFILE=default

EXPOSE 8091
RUN echo "java -Dspring.profiles.active=\$PROFILE \$JVM_OPTS -jar ${APP_JAR_FILE}" > ./entrypoint.sh
ENTRYPOINT ["sh", "./entrypoint.sh"]
