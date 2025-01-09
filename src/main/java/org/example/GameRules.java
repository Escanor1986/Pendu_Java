package org.example;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.text.RandomStringGenerator;

public abstract class GameRules {
    protected boolean IsOver;
    protected HashMap<String, Integer> lifePoints;
    protected String randomWord;
    protected HashMap<Integer, String> resultMap;
    protected TreeSet<Character> charAttempt;

    public GameRules() {
        lifePoints = new HashMap<>();
        lifePoints.put("Facile", 5);
        lifePoints.put("Moyen", 7);
        lifePoints.put("Difficile", 10);
    }

    private String createRandomWords() {
        int randomRangeWordSize = ThreadLocalRandom.current().nextInt(4, 15);
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        return generator.generate(randomRangeWordSize);
    }

    public String getRandomWord() {
        if (randomWord == null) {
            randomWord = createRandomWords();
        }
        return randomWord;
    }

    public boolean isGameOver() {
        return IsOver;
    }

    public void setGameOver(boolean isOver) {
        this.IsOver = isOver;
    }

    public abstract void demarrerPartie();
}
