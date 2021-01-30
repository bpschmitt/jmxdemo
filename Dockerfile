FROM openjdk:8-jdk-slim
WORKDIR /app
ADD src/ /app
RUN javac Main.java

CMD ["java", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=7199", "-Dcom.sun.management.jmxremote.rmi.port=7199", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "-Djava.rmi.server.hostname=0.0.0.0", "Main"]
