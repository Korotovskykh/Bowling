package org.enigma.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int FRAMES_TOTAL = 10;
    private static final int STRIKE_SCORE = 10;

    private List<Frame> frames = new ArrayList<Frame>(FRAMES_TOTAL);
    private Frame currentFrame;
    private boolean gameIsOver;
    private int score;

    {
        currentFrame = new Frame(1);
        frames.add(currentFrame);
    }

    public boolean isOver() {
        return gameIsOver;
    }

    public void roll(int score) {
        if (!gameIsOver) {
            if (currentFrame.isFinished()) {
                currentFrame = new Frame(frames.size() + 1);
                frames.add(currentFrame);
            }
            currentFrame.roll(score);
            if (currentFrame.getFrameNumber() == FRAMES_TOTAL & currentFrame.isFinished()) {
                gameIsOver = true;
                countPoints();
//                System.out.println("\nGame is over.");
            }
        } else {
//            System.out.println("Sorry, game is over.");
        }
    }

    public void roll() {
        if (!gameIsOver) {
            if (currentFrame.isFinished()) {
                currentFrame = new Frame(frames.size() + 1);
                frames.add(currentFrame);
            }
            currentFrame.roll();
            if (currentFrame.getFrameNumber() == FRAMES_TOTAL & currentFrame.isFinished()) {
                gameIsOver = true;
                countPoints();
//                System.out.println("\nGame is over.");
            }
        } else {
//            System.out.println("Sorry, game is over.");
        }
    }


    public void printFrames() {
        for (Frame frame : frames) {
            System.out.println(frame);
        }
    }

    public void countPoints() {
        // count total points only when game is over
        if (gameIsOver) {
            // frames 1-9
            for (int i = 0; i + 1 < FRAMES_TOTAL; i++) {
                // if strike
                if (frames.get(i).isStrike()) {
                    if (frames.get(i + 1).getRolls().size() == 1) {
                        frames.get(i).setFrameScore(STRIKE_SCORE * 2 + frames.get(i + 2).getRolls().get(0));
                    } else {
                        frames.get(i).setFrameScore(STRIKE_SCORE + frames.get(i + 1).getRolls().get(0) + frames.get(i + 1).getRolls().get(1));
                    }
                // if spare
                } else if (frames.get(i).isSpare()) {
                    frames.get(i).setFrameScore(STRIKE_SCORE + frames.get(i + 1).getRolls().get(0));
                // if opened
                } else {
                    frames.get(i).setFrameScore(frames.get(i).getRolls().get(0) + frames.get(i).getRolls().get(1));
                }
            }

            // frame 10
            int frameScore = 0;
            for (Integer i : frames.get(FRAMES_TOTAL - 1).getRolls()) {
                frameScore += i;
            }
            frames.get(FRAMES_TOTAL - 1).setFrameScore(frameScore);

            for (Frame frame : frames) {
                score += frame.getFrameScore();
            }
        } else {
            System.out.println("Game is not over yet.");
        }
    }

    public int getScore() {
        return score;
    }

    public void printScore() {
        if (gameIsOver) {
            System.out.println("TOTAL POINTS: " + score);
        } else {
            System.out.println("Game is not over yet.");
        }
    }
}
