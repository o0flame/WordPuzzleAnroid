package edu.gatech.seclass.sdpscramble.model;

import java.util.HashSet;

/**
 * Created by panpan on 10/10/17.
 */

public class CreatedScrambleList {
    private String playerID;
    private HashSet<String> createdScrambleList = new HashSet<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public HashSet<String> getCreatedScrambleList() {
        return createdScrambleList;
    }

    public void setCreatedScrambleList(HashSet<String> createdScrambleList) {
        this.createdScrambleList = createdScrambleList;
    }
}
