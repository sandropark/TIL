package com.sandro.bowling;

public class Game {
    public static final int MAX_FRAME = 10;
    private static final int MAX_ROLL = 21;
    private final int[] rolls = new int[MAX_ROLL];
    private int currentRoll;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        int firstRollInFrame = 0;
        for (int frame = 0; frame < MAX_FRAME; frame++) {
            if (isStrike(firstRollInFrame)) {
                score += 10 + nextTwoBallsForStrike(firstRollInFrame);
                firstRollInFrame--;
            } else if (isSpare(firstRollInFrame)) {
                    score += 10 + nextBallForSpare(firstRollInFrame);
            } else {
                score += BallsForThisFrame(firstRollInFrame);
            }
            firstRollInFrame += 2;
        }
        return score;
    }

    private int nextBallForSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame + 2];
    }

    private int nextTwoBallsForStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame + 1] + rolls[firstRollInFrame + 2];
    }

    private boolean isStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame] == 10;
    }

    private boolean isSpare(int firstRollInFrame) {
        return BallsForThisFrame(firstRollInFrame) == 10;
    }

    private int BallsForThisFrame(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1];
    }

}
