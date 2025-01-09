package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Classe abstraite définissant les règles du jeu du pendu.
 * <p>
 * Cette classe contient les propriétés et les comportements communs à toutes
 * les parties de pendu. Les classes qui héritent de cette classe doivent
 * implémenter la méthode {@link #demarrerPartie()}.
 * </p>
 */
public abstract class GameRules {
    protected HashMap<String, Integer> difficulty;
    protected Integer lifePoints;
    protected List<String> listOfWords;
    protected String randomWord;
    protected HashMap<Integer, String> resultMap;
    protected TreeSet<Character> charAttempt;

    private static final Logger logger = LogManager.getLogger(GameRules.class);

    /**
     * Constructeur de la classe GameRules.
     * <p>
     * Initialise les niveaux de difficulté et leurs points de vie associés.
     * </p>
     */
    public GameRules() {
        difficulty = new HashMap<>();
        difficulty.put("Facile", 10);
        difficulty.put("Moyen", 7);
        difficulty.put("Difficile", 5);
    }

    /**
     * Charge la liste des mots depuis un fichier.
     *
     * @param filePath Chemin du fichier contenant les mots.
     * @return Une liste de mots.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    private static List<@NotNull String> loadWords(String filePath) throws IOException {
        try {
            return Files.readAllLines(
                            // Ici on vérifie que la ressource a bien été trouvée SINON ==> exception explicite
                    Paths.get(Objects.requireNonNull
                            // Charge le fichier words.txt depuis le classpath (src/main/ressources/)
                            // Renvoie un URL qui pointe vers la ressource
                            (GameRules.class.getClassLoader().getResource(filePath))
                            // COnvertit l'URL en URI pour que Paths.get() fonctionne correctemment
                            .toURI())
            );
        } catch (Exception e) {
            throw new IOException("Impossible de charger les mots depuis le fichier : " + filePath, e);
        }
    }

    /**
     * Permet au joueur de choisir un niveau de difficulté.
     *
     * @return Le nombre de points de vie correspondant au niveau choisi.
     * @throws IOException Si une erreur d'entrée/sortie survient.
     */
    protected Integer getDifficultyLevel() throws IOException {
        try {
            logger.info("Sélection du niveau de difficulté par le joueur");
            System.out.println("""
                    Veuillez choisir un niveau de difficulté pour la partie (Mot exacte !) :\
                    
                    \t Facile - (10 ❤️). \
                    
                    \t Moyen - (7 ❤️). \
                    
                    \t Difficile - (5 ❤️). \
                    """);

            logger.info("Début capture de l'entrée utilisateur dans la console");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String response = reader.readLine();

            if (response.equals("Facile")) {
                lifePoints = difficulty.get(response);
            } else if (response.equals("Moyen")) {
                lifePoints = difficulty.get(response);
            } else if (response.equals("Difficile")) {
                lifePoints = difficulty.get(response);
            } else {
                System.out.println("Veuillez choisir entre : 'Facile' - 'Moyen' - 'Difficile' !");
                Thread.sleep(1000);
                this.getDifficultyLevel();
            }
            System.out.printf("Merci ! Vous avez choisi le niveau de difficulté : %s, vous aurez donc : %d ❤\uFE0F !%n", response, lifePoints);
            logger.debug("Choix du niveau de difficulté : {}, et nombres de points de vie : {}.", response, lifePoints);
            return lifePoints;
        } catch (Exception e) {
            logger.error("Erreur lors de la sélection du niveau de difficulté : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Effectue les tentatives du joueur pour deviner le mot.
     * <p>
     * Cette méthode gère les entrées du joueur, les vérifications de lettres,
     * et la mise à jour des points de vie.
     * </p>
     */
    protected void attemptToWin() {
        resultMap = new HashMap<Integer, String>();
        charAttempt = new TreeSet<Character>();
        while (lifePoints > 0 || charAttempt.size() < randomWord.length() - 1) {
            logger.info("Début des tentatives pour trouver le mot caché");
            try {
                System.out.printf("Vous disposez de %s tentatives pour trouver le mot mystère : \n", lifePoints);

                logger.info("Entrée d'une lettre par l'utilisateur");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String response = reader.readLine();

                logger.info("Vérifie que l'utilisateur n'entre pas autre chose qu'une lettre.");
                if (response == null || response.length() != 1 || !Character.isLetter(response.charAt(0))) {
                    System.out.println("Veuillez entrer une seule lettre valide !");
                    continue;
                }

                char letter = response.charAt(0);

                logger.debug("On test la présence de l'entrée utilisateur : {} dans le mot mystère : {}. \n", letter, randomWord);
                if (randomWord.contains(String.valueOf(letter))) {
                    logger.debug("On test la présence de l'entrée utilisateur : {} dans la liste des lettres déjà tentée : {}. \n", letter, charAttempt.contains(letter));
                    if (charAttempt.contains(letter)) {
                        System.out.printf("Vous avez déjà essayé la lettre %d ! \n", letter);
                        Thread.sleep(1000);
                    } else {
                        int letterRandomWordIndex = randomWord.indexOf(String.valueOf(letter)) + 1;
                        System.out.printf("Bravo ! La lettre '%s' se trouve bien dans le mot mystère à l'emplacement '%d' ! \n", letter, letterRandomWordIndex);
                        resultMap.put(letterRandomWordIndex, String.valueOf(letter));
                        charAttempt.add(letter);
                    }
                } else {
                    lifePoints -= 1;
                    charAttempt.add(letter);
                    System.out.printf("Oups ! Le mot mystère ne contient pas la lettre '%s' ! Il vous reste %d tentatives ! \n", letter, lifePoints);
                    if (lifePoints == 0) {
                       System.out.println("Perdu ! Désolé :-) Retentez une partie !");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sélectionne un mot aléatoire depuis la liste des mots.
     *
     * @return Un mot aléatoire.
     */
    protected String getRandomWord() {
        try {
            listOfWords = loadWords("words.txt");
            logger.info("Mots chargés : {}", listOfWords);
            int length = listOfWords.size();
            if (length > 0) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, length);
                randomWord = listOfWords.get(randomIndex);
            } else {
                throw new IllegalStateException("La liste de mots est vide !");
            }
            logger.debug("Mot aléatoire choisi, {}", randomWord);
           // System.out.printf("Mot aléatoire choisi, %s \n", randomWord); // A EFFACER, UNIQUEMENT POUR TESTER !
            return randomWord;
        } catch (IOException e) {
            logger.error("Erreur lors du chargement du fichier de mots : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode abstraite pour démarrer une partie.
     *
     * @throws IOException Si une erreur d'entrée/sortie survient.
     * @throws InterruptedException Si une interruption du thread survient.
     */
    public abstract void demarrerPartie() throws IOException, InterruptedException;
}
