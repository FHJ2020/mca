FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
WORKDIR /home
ADD target/mca-1.0-SNAPSHOT-jar-with-dependencies.jar /home/app.jar
RUN touch /home/input

RUN apk add --no-cache tini
RUN apk add --no-cache  htop

#RUN apk add  tini
#RUN apk add  htop
#-XX:+UseParallelGC
#-Xms100m
#-Xmx100m
#-XX:+PrintGCDetails
#-Xlog:gc*=debug

ENTRYPOINT ["/sbin/tini", "--", "java","-Xlog:gc*=debug","-XX:+UseParallelGC","-Xmx500m","-Xmx500m","-Xss512K","-jar","/home/app.jar"]
#ENTRYPOINT ["java","-Xms750m","-Xmx900m","-server","-jar","/hello.jar"]
