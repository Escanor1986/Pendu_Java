package org.example;

public class Game extends GameRules {

    public Game() {
        super();
        System.out.println("Nouvelle partie démarrée avec les règles du jeu !");
    }

    @Override
    public void demarrerPartie() {
        System.out.println("Bienvenue dans le jeu du pendu :");
        System.out.println("Le mot aléatoire à une longueur de : " + getRandomWord().length() + " caractère !");
        System.out.println("Bon jeu !");
    }



}
