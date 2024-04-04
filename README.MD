# Projet Monopoly en Java

## Description

Ce projet est une implémentation en Java du jeu de société classique Monopoly. Il a été développé dans le cadre d'un cours de programmation à l'université. Le jeu permet à plusieurs joueurs de jouer ensemble sur un plateau de jeu virtuel, en achetant des propriétés et en essayant de rendre les autres joueurs fauchés.

## Fonctionnalités

- Jeu multijoueur en local.
- Plateau de jeu classique avec toutes les propriétés, les chances, et les caisses de communauté.
- Gestion des transactions, y compris l'achat de propriétés, le paiement des loyers et des taxes.
- Gestion des cartes Chance et Caisse de Communauté.
- Prise en charge de la prison, y compris aller en prison, payer pour sortir.

## Comment jouer

1. Cloner le dépôt GitHub sur votre machine locale.
2. Assurez-vous d'avoir Java installé sur votre machine.
3. Compilez le projet en utilisant Maven : `mvn clean install`.
4. Exécutez le jeu à partir de la ligne de commande ou de votre IDE préféré.
5. Suivez les instructions à l'écran pour jouer.

## Itérations

Le projet a été développé en plusieurs itérations. Chaque itération ajoute de nouvelles fonctionnalités au jeu et améliore son architecture. Les itérations précédentes sont disponibles dans les dossiers `iteration1`, `iteration2`, `iteration3` et `iteration4`.

- **Itération 1** : Création d'une représentation textuelle basique du plateau de jeu avec les cases, les propriétés, et les joueurs.
- **Itération 2** : Ajout d'une gestion basique de l'argent des joueurs.spéciales.
- **Itération 3** : Intégration des propriétés spéciales comme les gares et les services publics.
- **Itération 4** : Refonte de l'architecture du jeu, ajout des cartes Chance et Caisse de communauté, et gestion des actions des cartes.

## Dépendances

- Java JDK 11 ou supérieur.
- Maven pour la gestion des dépendances et la compilation du projet.
- JUnit pour les tests unitaires.


## Architecture du projet

Le projet est structuré comme suit :

- `src/main/java/iteration4`: Contient le code source du jeu.
- `src/test/java/iteration4`: Contient les tests unitaires du projet.
 
- `diagram`: Contient les diagrammes UML des différentes itérations du projet.
- `document` : contient le raport ainssi que la présentation du projet

## Diagrammes UML

Les diagrammes UML de conception du projet sont disponibles dans le dossier `diagram`. Ils ont été créés à l'aide de l'outil [Draw.io](https://app.diagrams.net/).


## Auteurs

- [Frédéric Turcq-santucci](https://github.com/fturcq-santucci)
- [Stéphane Thiry](https://github.com/Stephanethr)
- [Jacques Battaglini](https://github.com/Heeko2b)
