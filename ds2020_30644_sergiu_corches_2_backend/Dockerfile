FROM maven:3.6.3-jdk-11 AS builder

COPY ./src/ /root/src
COPY ./pom.xml /root/
COPY ./checkstyle.xml /root/
WORKDIR /root
RUN mvn package
RUN java -Djarmode=layertools -jar /root/target/Back-1.0-SNAPSHOT.jar list
RUN java -Djarmode=layertools -jar /root/target/Back-1.0-SNAPSHOT.jar extract
RUN ls -l /root

FROM openjdk:11.0.6-jre

ENV TZ=UTC
ENV DB_IP=ec2-54-246-67-245.eu-west-1.compute.amazonaws.com
ENV DB_PORT=5432
ENV DB_USER=hawmzsfxnsqlnc
ENV DB_PASSWORD=4059fc5bb23dc7983cad75a7d8616d61e25dd007bcd3167c84f9ccbf0d293ecb
ENV DB_DBNAME=d44rpc9d4jopnp


COPY --from=builder /root/dependencies/ ./
COPY --from=builder /root/snapshot-dependencies/ ./

RUN sleep 10
COPY --from=builder /root/spring-boot-loader/ ./
COPY --from=builder /root/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","-XX:+UseContainerSupport -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Xms512m -Xmx512m -XX:+UseG1GC -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m"]