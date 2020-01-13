# OpenClassrooms

Ce dépôt contient une mini-application pour le P3 du parcours **Grande École du Numérique**.

Le projet :
===========
Titre du projet : Entrevoisins
------------------------------
L'objectif de ce projet est de compléter une fonctionnalité et d'en créer une deuxième.
Les tests unitaires et instrumentalisés doivent s'exécutés sans échouer.

Fonctionnalités complété et créée :
    - compléter l'onglet "Favori" :
        - alimenter la liste des voisins qui font parti des voisins favoris
        - supprimer un voisin de la liste des favoris sans le supprimer de la liste globale des voisins

    - créer un nouvel écran contenant le détail d'un voisin :
        - renseigner les informations du voisin
        - rendre le voisin affiché en tant que favori ou non en cliquant sur le bouton "Favori" (étoile)


Le programme :
==============
Adresse GitHub du programme :
-----------------------------
    https://github.com/titouane74/MagicGithub.git

Installation :
------------
    - Télécharger le repository dans votre environnement local
    - Ouvrir Android Studio  et importer le projet Entrevoisin

Exécution :
-----------
    - Choisir l'item "app" du Run/Debug configuration
    - Exécuter l'application


Les tests :
===========
Exécution et résultat des tests unitaires :
-------------------------------

    Dans le repository : Entrevoisins/app/src/test/java/com/openclassrooms/entrevoisins/service

	- ouvrir le fichier : NeighbourServiceTest.java du repository
	- lancer l'exécution sur la class NeighbourServiceTest
	- résultat d'exécution dans le reposotiry Entrevoisins/TestResult/ :  Passed - Test Results - NeighbourServiceTest.html

Exécutions et résultats des tests instrumentalisés :
--------------------------------------

	Dans le repository : Entrevoisins/app/src/androidTest/java/com/openclassrooms/entrevoisins/ui/neihgbour_list/

	- ouvrir le fichier : FavoriFragmentTest.java
	- lancer l'exécution sur la class FavoriFragmentTest
	- résultat d'exécution dans le reposotiry Entrevoisins/TestResult/ :  Passed - Test Results - FavoriFragmentTest.html

	- ouvrir le fichier : NeighbourActivityTest.java
	- lancer l'exécution sur la class NeighbourActivityTest
	- résultat d'exécution dans le reposotiry Entrevoisins/TestResult/ :  Passed - Test Results - NeighbourActivityTest.html

La License :
============
OpenClassrooms