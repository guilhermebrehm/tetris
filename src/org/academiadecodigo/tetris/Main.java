package org.academiadecodigo.tetris;

public class Main {
    public static void main(String[] args) {
        Game g = Game.getInstance();
        g.init();
        g.start();
    }
}
