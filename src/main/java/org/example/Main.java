package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
     * Cette méthode démarre une partie de pendu.
     * </p>
     *
     * @param args Les arguments passés au programme depuis la ligne de commande.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     * @throws InterruptedException Si une interruption du thread survient.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean replay = true;

        while (replay) {
            Game game = new Game();
            game.demarrerPartie();

            System.out.println("Souhaitez-vous rejouer une autre partie : OUI ou NON (en toutes lettres majuscules svp) ?");
            String response = reader.readLine();

            if ("NON".equals(response)) {
                replay = false;
                System.out.println("Merci d'avoir joué ! À bientôt.");
            } else if (!"OUI".equals(response)) {
                System.out.println("Veuillez entrer une réponse valide (OUI ou NON).");
            }
        }
    }
}
