package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Classe qui représente une partie de pendu.
 * <p>
 * Cette classe hérite des règles du jeu définies dans {@link GameRules}.
 * Elle implémente les actions spécifiques à une partie de jeu.
 * </p>
 *
 * @see GameRules
 */
public class Game extends GameRules {

    private static final Logger logger = LogManager.getLogger(Game.class);

    /**
     * Constructeur de la classe Game.
     * <p>
     * Initialise une nouvelle partie avec les règles du jeu.
     * </p>
     */
    public Game() {
        super();
        System.out.println("Nouvelle partie démarrée avec les règles du jeu !");
    }

    /**
     * Démarre une partie de pendu.
     * <p>
     * Cette méthode affiche les instructions de jeu, demande la sélection
     * d'un niveau de difficulté et commence les tentatives pour deviner le mot.
     * </p>
     *
     * @throws IOException Si une erreur d'entrée/sortie survient.
     * @throws InterruptedException Si une interruption du thread survient.
     */
    @Override
    public void demarrerPartie() throws IOException, InterruptedException {
        logger.info("Démarrage de la partie.");
        System.out.println("Bienvenue dans le jeu du pendu :");
        System.out.println("Le mot aléatoire à une longueur de : " + getRandomWord().length() + " caractère !");
        System.out.println("Bon jeu !");
        Thread.sleep(1000);
        logger.info("Sélection du niveau de difficulté de la partie");
        getDifficultyLevel();
        Thread.sleep(1000);
        attemptToWin();
    }
}
