FROM debian:latest

# Installer java 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean;

# Installer XVFB (nécessaire pour l'interface)
RUN apk update && apk add xvfb libxtst-dev libxrender-dev

# Cloner le programme Java depuis le dépot github
RUN apt-get update && \
    apt-get install -y git && \
    git clone https://github.com/MatKim76/docker-sae203.git /usr/src/app/docker-sae203 && \
    apt-get remove -y git && \
    apt-get clean;

# installer un serveur X11 (interface graphique)
RUN apt-get install -y x11-apps;

# Définir l'affichage à utiliser pour le serveur X11
ENV DISPLAY=host.docker.internal:0.0

# Changer de répertoire
WORKDIR /usr/src/app/docker-sae203/java

# Compiler le programme Java en utf8
RUN javac -encoding UTF-8 *.java

# Lancer l'interface graphique et l'application java
CMD ["bash", "-c", "Xvfb :0 -screen 0 1024x768x16 & java Controleur"]
#CMD ["java", "-Djava.awt.headless=false", "Controleur"]

# Exposer le port 8088
EXPOSE 8080
