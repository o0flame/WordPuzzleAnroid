package edu.gatech.seclass.sdpscramble.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by panpan on 10/10/17.
 */

public class UnSolvedScrambleList {
    private String playerID;
    private HashSet<String> unsolvedScrambleList = new HashSet<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public HashSet<String> getUnsolvedScrambleList() {
        return unsolvedScrambleList;
    }

    public void setUnsolvedScrambleList(HashSet<String> unsolvedScrambleList) {
        this.unsolvedScrambleList = unsolvedScrambleList;
    }
}
