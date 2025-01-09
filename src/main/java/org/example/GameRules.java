package org.example;

import java.io.IOException;
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
        difficulty.put("Facile", 5);
        difficulty.put("Moyen", 7);
        difficulty.put("Difficile", 10);
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
    return null;
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

    public abstract void demarrerPartie();
}
