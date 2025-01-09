# Jeu du Pendu en Java

## Description
Ce projet est une implémentation console du classique jeu du Pendu, développé en Java. Les joueurs tentent de deviner un mot secret en proposant des lettres une à une avant d'épuiser leurs points de vie.

Le projet est éducatif et met en avant les concepts de programmation orientée objet (POO), la gestion des exceptions, et l'utilisation de bibliothèques tierces comme **Log4j** pour la journalisation.

---

## Fonctionnalités
- Choix aléatoire d'un mot à deviner parmi une liste de mots.
- Gestion de la difficulté (« Facile », « Moyen », « Difficile »).
- Affichage des progrès du joueur et des lettres déjà tentées.
- Gestion des erreurs et affichage des journaux avec **Log4j**.

---

## Prérequis
### Outils requis
- **Java JDK** 17 ou version ultérieure
- **Maven** pour la gestion des dépendances

### Structure des fichiers
Voici la structure du projet :

```
pendu-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── Main.java
│   │   │       ├── Game.java
│   │   │       └── GameRules.java
│   │   └── resources/
│   │       └── words.txt
│   └── test/
├── pom.xml
└── README.md
```

---

## Installation
### 1. Cloner le projet
```bash
git clone https://github.com/votre-utilisateur/pendu-java.git
cd pendu-java
```

### 2. Ajouter les dépendances
Exécutez la commande suivante pour télécharger les dépendances définies dans `pom.xml` :
```bash
mvn clean install
```

---

## Configuration
### 1. Fichier `words.txt`
Le fichier `words.txt`, situé dans `src/main/resources/`, contient les mots que le joueur devra deviner. Vous pouvez le modifier pour ajouter vos propres mots, un par ligne.

Exemple :
```
ordinateur
programmation
java
code
clavier
```

### 2. Configuration du Logger
Le fichier `log4j2.xml`, situé dans `src/main/resources/`, permet de configurer la journalisation. Par défaut, les messages s'affichent dans la console avec un format simple et lisible.

---

## Utilisation
1. Compilez le projet :
```bash
mvn compile
```

2. Lancez le jeu depuis la classe `Main` :
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

3. Suivez les instructions dans la console pour jouer !

---

## Dépendances Maven
Voici les principales dépendances utilisées dans ce projet :

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-text</artifactId>
        <version>1.10.0</version>
    </dependency>
    <dependency>
        <groupId>org.jetbrains</groupId>
        <artifactId>annotations</artifactId>
        <version>RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.20.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.20.0</version>
    </dependency>
</dependencies>
```

---

## Améliorations futures
- Ajouter une interface graphique (JavaFX ou Swing).
- Intégrer un système de score pour suivre les performances des joueurs.
- Ajouter des statistiques pour analyser les mots les plus difficiles à deviner.

---


## Licence
Ce projet est distribué sous la licence MIT. Vous êtes libre de l'utiliser, le modifier et le redistribuer.

