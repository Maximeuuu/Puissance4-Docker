FROM debian:latest

# Installer java 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# installer un serveur X11 (interface graphique)
RUN apt-get install -y x11-apps;

# Définir l'affichage à utiliser pour le serveur X11
ENV DISPLAY=host.docker.internal:0.0

# "docker2" parce que DOCKER C COOL ! :D

# Cloner le programme Java depuis le dépot github
RUN apt-get install -y git && \
    git clone https://github.com/MatKim76/docker-sae203.git /usr/src/app/docker2-sae203  && \
    apt-get clean;

# ajouter au classpath le JAR pour le Clavier
ENV CLASSPATH /usr/src/app/docker2-sae/javaFinal/iut.jar

# compiler le projet
RUN cd /usr/src/app/docker2-sae203/javaFinal && \
	javac -encoding UTF-8 *.java;

# Exécution de l'application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-cp", "/usr/src/app/docker2-sae203/javaFinal", "Controleur"]
