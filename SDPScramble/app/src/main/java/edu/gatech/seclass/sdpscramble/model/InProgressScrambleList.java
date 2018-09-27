package edu.gatech.seclass.sdpscramble.model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by panpan on 10/10/17.
 */

public class InProgressScrambleList {
    private String playerID;
    private HashMap<String, HashSet<InProgressScramble>> inProgressScrambleList = new HashMap<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public HashMap<String, HashSet<InProgressScramble>> getInProgressScrambleList() {
        return inProgressScrambleList;
    }

    public void setInProgressScrambleList(HashMap<String, HashSet<InProgressScramble>> inProgressScrambleList) {
        this.inProgressScrambleList = inProgressScrambleList;
    }
}
