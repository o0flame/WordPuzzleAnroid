package edu.gatech.seclass.sdpscramble.model;

/**
 * Created by panpan on 10/9/17.
 */

public class Scramble {
    private String phraseNotScrambled;
    private String clue;
    private String scrambleIdentifier;
    private String phraseScrambled;
    private String creatorID;
    private int numberOfTimesSolved;


    public String getPhraseNotScrambled() {
        return phraseNotScrambled;
    }

    public void setPhraseNotScrambled(String phraseNotScrambled) {
        this.phraseNotScrambled = phraseNotScrambled;
    }

    public String getClue() {
        return clue == null ? "" : clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String getScrambleIdentifier() {
        return scrambleIdentifier;
    }

    public void setScrambleIdentifier(String scrambleIdentifier) {
        this.scrambleIdentifier = scrambleIdentifier;
    }

    public String getPhraseScrambled() {
        return phraseScrambled;
    }

    public void setPhraseScrambled(String phraseScrambled) {
        this.phraseScrambled = phraseScrambled;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public int getNumberOfTimesSolved() {
        return numberOfTimesSolved;
    }

    public void setNumberOfTimesSolved(int numberOfTimesSolved) {
        this.numberOfTimesSolved = numberOfTimesSolved;
    }
}
