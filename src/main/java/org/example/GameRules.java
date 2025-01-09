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

public abstract class GameRules {
    protected boolean IsOver;
    protected HashMap<String, Integer> difficulty;
    protected Integer lifePoints;
    protected List<String> listOfWords;
    protected String randomWord;
    protected HashMap<Integer, String> resultMap;
    protected TreeSet<Character> charAttempt;

    private static final Logger logger = LogManager.getLogger(GameRules.class);

    public GameRules() {
        difficulty = new HashMap<>();
        difficulty.put("Facile", 10);
        difficulty.put("Moyen", 7);
        difficulty.put("Difficile", 5);
    }

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

    protected void attemptToWin() {
        resultMap = new HashMap<Integer, String>();
        charAttempt = new TreeSet<Character>();
        while (lifePoints > 0) {
            logger.info("Début des tentatives pour trouver le mot caché");
            try {
                System.out.printf("Vous disposez de %s tentatives pour trouver le mot mystère :", lifePoints);

                logger.info("Entrée d'une lettre par l'utilisateur");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String response = reader.readLine();
                char letter = response.charAt(0);

                // Pour vérifier si une lettre est présente dans le mot aléatoire
                // Il faut d'abord convertir char en String
                if (randomWord.contains(String.valueOf(letter))) {
                    if (charAttempt.contains(letter)) {
                        System.out.printf("Vous avez déjà essayé la lettre %d !", letter);
                        break;
                    } else {
                        resultMap.put(randomWord.indexOf(String.valueOf(letter)), String.valueOf(letter));

                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

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
            return randomWord;
        } catch (IOException e) {
            logger.error("Erreur lors du chargement du fichier de mots : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public boolean isGameOver() {
        return IsOver;
    }

    public void setGameOver(boolean isOver) {
        this.IsOver = isOver;
    }

    public abstract void demarrerPartie() throws IOException, InterruptedException;
}
