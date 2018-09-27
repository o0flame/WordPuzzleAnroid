package edu.gatech.seclass.sdpscramble.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by panpan on 10/10/17.
 */

public class SolvedScrambleList {
    private String playerID;
    private HashSet<String> solvedScrambleList = new HashSet<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public HashSet<String> getSolvedScrambleList() {
        return solvedScrambleList;
    }

    public void setSolvedScrambleList(HashSet<String> solvedScrambleList) {
        this.solvedScrambleList = solvedScrambleList;
    }
}
