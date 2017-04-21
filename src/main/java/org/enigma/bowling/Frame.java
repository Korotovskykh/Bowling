package org.enigma.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int PINS = 10;
    private static final int FRAMES_TOTAL = 10;
    private static final int MAX_ROLLS_COMMON_FRAME = 2;
    private static final int MAX_ROLLS_LAST_FRAME = 3;

    private List<Integer> rolls = new ArrayList<Integer>(MAX_ROLLS_LAST_FRAME);
    private int frameNumber;
    private int frameScore;
    private int pinsLeft = PINS;
    private boolean isFinished;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public List<Integer> getRolls() {
        return rolls;
    }

    public void roll() {
        roll((int) (Math.random() * (pinsLeft + 1)));
    }

    public void roll(int score) {
        rolls.add(score);
        pinsLeft -= score;
        if (frameNumber < FRAMES_TOTAL) {
            if (pinsLeft == 0 || rolls.size() == MAX_ROLLS_COMMON_FRAME) {
                isFinished = true;
            }
        } else {
            if (rolls.size() == MAX_ROLLS_LAST_FRAME || rolls.size() == MAX_ROLLS_COMMON_FRAME && !isStrike() && !isSpare()) {
                isFinished = true;
            }
            //refresh pins after strike or spare in Frame 10
            if (pinsLeft == 0 && rolls.size() < MAX_ROLLS_LAST_FRAME) {
                pinsLeft = PINS;
            }
        }
    }

    public boolean isStrike() {
        return rolls.get(0) == PINS;
    }

    public boolean isSpare() {
            return rolls.size() == MAX_ROLLS_COMMON_FRAME & rolls.get(0) + rolls.get(1) == PINS;
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        String listOfThrows = String.format("Frame %d:\t", frameNumber);
        for (Integer i : rolls) {
            listOfThrows += i.toString() + " ";
        }
        listOfThrows += "\nPoints:\t\t" + frameScore + "\n";
        return listOfThrows;
    }
}
