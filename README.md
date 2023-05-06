# Docker - Puissance4

Sa        Matéo   - B2

Baillobay Trystan - B1

Lemoine   Maxime  - B2

A faire :

- DockerFile

- ReadMe.md

- site internet pur la présentation

## Instruction pour lancer le Puissance4

1. Installer un serveur X11 pour windows.
2. Télécharger depuis le github l'[image Docker](https://github.com/MatKim76/docker-sae203/blob/8f5a2292059c602d4a4715eaf883eccddfebda84/Dockerfile) ``Dockerfile``.
3. Exécuter l'application Docker depuis le systeme d'exploitation actuel.

Dans un terminal
1. Vérifier que docker est installé : ``docker --version``.
2. Se déplacer dans le répertoire où se situe l'image ``Dockerfile`` : ``cd <repertoire>``.
3. Construire l'image : ``docker build -t img-puissance4 .``
4. Fermer le terminal.

Dans un premier terminal
1. Lancer le serveur : ``docker run -it --rm -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix img-puissance4``.
2. Vérifier si le conteneur existe : ``docker ps``.
*info : 1 ligne devrait s'afficher*.
3. Une fenêtre s'ouvre, on complète les entrées.
4. Appuyer sur le bouton "Créer salle".

Dans un second terminal
1. Lancer le serveur : ``docker run -it --rm -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix img-puissance4``.
2. Vérifier si le conteneur existe : ``docker ps``.
*info : 2 lignes devraient s'afficher*
3. Une fenêtre s'ouvre, on complète les entrées.
*info : dans ip mettre : ``172.17.0.2``*
4. Appuyer sur le bouton "rejoindre salle".
 
Vous pouvez jouer via les deux terminals.
