package edu.gatech.seclass.sdpscramble.model;

/**
 * Created by panpan on 10/10/17.
 */

public class InProgressScramble {
    private String playerID;
    private String uniqueIdentifier;
    private String inputWord;

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

    public String getInputWord() {
        return inputWord;
    }

    public void setInputWord(String inputWord) {
        this.inputWord = inputWord;
    }
}
