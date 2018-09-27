package edu.gatech.seclass.sdpscramble.model;

/**
 * Created by panpan on 10/9/17.
 */

public class ScrambleRecord {
    private String playerID;
    private String uniqueIdentifier;
    private int numberofTimesSolved;
    private boolean isSolved;
    private boolean isCreator;
    private boolean isInProgress;

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public int getNumberofTimesSolved() {
        return numberofTimesSolved;
    }

    public void setNumberofTimesSolved(int numberofTimesSolved) {
        this.numberofTimesSolved = numberofTimesSolved;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public void setCreator(boolean creator) {
        isCreator = creator;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }
}
