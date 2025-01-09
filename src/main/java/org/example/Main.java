package org.example;

import java.io.IOException;

/**
 * Classe principale de l'application.
 * <p>
 * Cette classe contient le point d'entrée `main` du programme : Mini projet "Pendu".
 * </p>
 *
 * <strong>Exemple d'utilisation :</strong>
 * <pre>
 *     Main.main(new String[]{});
 * </pre>
 *
 * @author Lionel Zovi
 * @version 1.0
 * @since 2024-12-20
 */
public class Main {

    /**
     * Point d'entrée principal de l'application.
     * <p>
     * Cette méthode exécute deux démonstrations :
     * <ul>
     *     <li>Une addition de deux nombres entiers</li>
     *     <li>Un affichage de plusieurs chaînes de caractères</li>
     * </ul>
     * </p>
     *
     * @param args Les arguments passés au programme depuis la ligne de commande.
     */
    public static void main(String[] args) throws IOException {
        Game game = new Game();

        game.demarrerPartie();
    }

}