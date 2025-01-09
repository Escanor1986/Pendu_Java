package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Game extends GameRules {

    private static final Logger logger = LogManager.getLogger(Game.class);

    public Game() {
        super();
        System.out.println("Nouvelle partie démarrée avec les règles du jeu !");
    }

    @Override
    public void demarrerPartie() throws IOException {
        logger.info("Démarrage de la partie.");
        System.out.println("Bienvenue dans le jeu du pendu :");
        System.out.println("Le mot aléatoire à une longueur de : " + getRandomWord().length() + " caractère !");
        System.out.println("Bon jeu !");
        getDifficultyLevel();
    }



}
