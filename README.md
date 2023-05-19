# Docker - Puissance4

Sa        Matéo   - B2

Baillobay Trystan - B1

Lemoine   Maxime  - B2

* * *

## Instructions pour lancer le Puissance4

### Première installation (à faire une fois)

#### Téléchargements
1. Installer un serveur X11 (exemple : Xming pour windows).
2. Télécharger depuis le github l'[image Docker](https://github.com/MatKim76/docker-sae203/blob/8f5a2292059c602d4a4715eaf883eccddfebda84/Dockerfile) ``Dockerfile``.

#### Exécutions
1. Exécuter l'application de **serveur X11**.
2. Exécuter l'application **Docker**.

#### Dans un terminal
1. Vérifier que docker est installé : ``docker --version``.
2. Se déplacer dans le répertoire où se situe l'image ``Dockerfile`` : ``cd <repertoire>``.
3. Construire l'image : ``docker build -t img-puissance4 .``

*info : le processus peut prendre quelques secondes (2min environs)*
![build puissance4](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/docker_build_puissance4.png)

4. Fermer le terminal.

* * *

### Exécution (à faire à chaque fois pour jouer)

#### Exécutions
*info : passer si vous venez de suivre les instructions précédentes*
1. Exécuter l'application de **serveur X11**.
2. Exécuter l'application **Docker**.

#### Dans un premier terminal - Serveur
1. Lancer le serveur : ``docker run -it --rm -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix img-puissance4``.
2. Vérifier si le conteneur existe : ``docker ps``.
*info : 1 ligne devrait s'afficher*.
3. Une fenêtre s'ouvre, changer le port si nécessaire.
4. Appuyer sur le bouton "Créer salle".
![serveur](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/interface_serveur.png)
5. Récupérer l'ip affichée dans le terminal (*elle correspond à l'ip du serveur*)
![ip](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/ip_serveur.png)

#### Dans un second terminal - Joueur 1
1. Lancer le client : ``docker run -it --rm -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix img-puissance4``.
2. Vérifier si le conteneur existe : ``docker ps``.
*info : 2 lignes devraient s'afficher*
3. Une fenêtre s'ouvre, on complète les entrées.
*info : dans ip mettre : ``172.17.0.2`` ou l'ip du serveur*
4. Appuyer sur le bouton "rejoindre salle".
![joueur1](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/interface_joueur1.png)

#### Dans un troisième terminal - Joueur 2
1. Lancer le client : ``docker run -it --rm -e DISPLAY=host.docker.internal:0 -v /tmp/.X11-unix:/tmp/.X11-unix img-puissance4``.
2. Vérifier si le conteneur existe : ``docker ps``.
*info : 3 lignes devraient s'afficher*
3. Une fenêtre s'ouvre, on complète les entrées.
*info : dans ip mettre : ``172.17.0.2`` ou l'ip du serveur*
4. Appuyer sur le bouton "rejoindre salle".
![joueur2](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/interface_joueur2.png)

*info : vous devriez obtenir ceci dans le terminal du serveur*
![terminal puissance4](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/console_serveur.png)

**Vous pouvez jouer via les deux terminals clients !**
![partie de puissance 4](https://github.com/MatKim76/docker-sae203/blob/gh-pages/images/partie_2joueurs.png)
