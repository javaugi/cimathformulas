package com.sisllc.mathformulas.ci.ch08.ch88;

public class Question {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.getBoard().initialize();
        game.getBoard().printBoard();
        Automator automator = Automator.getInstance();
        while (!automator.isOver() && automator.playRandom()) {
        }
        automator.printScores();
    }
}
