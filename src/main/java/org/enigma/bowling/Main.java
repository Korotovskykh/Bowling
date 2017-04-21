package org.enigma.bowling;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        while (!game.isOver()) {
            game.roll(5);
        }

        game.printFrames();

        game.printScore();
    }
}
