package edu.gatech.seclass.sdpscramble.model;

import java.util.ArrayList;

/**
 * Created by panpan on 10/9/17.
 */

public class ScrambleStatistics {
    private String playerID;
    private ArrayList<ScrambleRecord> scrambleRecordList = new ArrayList<>();

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public ArrayList<ScrambleRecord> getScrambleRecordList() {
        return scrambleRecordList;
    }

    public void setScrambleRecordList(ArrayList<ScrambleRecord> scrambleRecordList) {
        this.scrambleRecordList = scrambleRecordList;
    }
}
