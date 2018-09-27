package edu.gatech.seclass.sdpscramble.model;

/**
 * Created by panpan on 10/9/17.
 */

public class PlayerRecord {
    private String uniqueUsername;
    private String playerFirstName;
    private String playerLastName;
    private int numberOfScrambleSolved;
    private int numberOfScrambleCreated;
    private int averageNumberOfTimesScrambleSolvedByOthers;

    public String getUniqueUsername() {
        return uniqueUsername;
    }

    public void setUniqueUsername(String uniqueUsername) {
        this.uniqueUsername = uniqueUsername;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public int getNumberOfScrambleSolved() {
        return numberOfScrambleSolved;
    }

    public void setNumberOfScrambleSolved(int numberOfScrambleSolved) {
        this.numberOfScrambleSolved = numberOfScrambleSolved;
    }

    public int getNumberOfScrambleCreated() {
        return numberOfScrambleCreated;
    }

    public void setNumberOfScrambleCreated(int numberOfScrambleCreated) {
        this.numberOfScrambleCreated = numberOfScrambleCreated;
    }

    public int getAverageNumberOfTimesScrambleSolvedByOthers() {
        return averageNumberOfTimesScrambleSolvedByOthers;
    }

    public void setAverageNumberOfTimesScrambleSolvedByOthers(int averageNumberOfTimesScrambleSolvedByOthers) {
        this.averageNumberOfTimesScrambleSolvedByOthers = averageNumberOfTimesScrambleSolvedByOthers;
    }
}
