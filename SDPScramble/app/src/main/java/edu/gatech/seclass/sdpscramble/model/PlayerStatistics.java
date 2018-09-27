package edu.gatech.seclass.sdpscramble.model;

import java.util.ArrayList;

/**
 * Created by panpan on 10/9/17.
 */

public class PlayerStatistics {
    private String uniqueUsername;
    private ArrayList<PlayerRecord> playerRecordList = new ArrayList<>();

    public String getUniqueUsername() {
        return uniqueUsername;
    }

    public void setUniqueUsername(String uniqueUsername) {
        this.uniqueUsername = uniqueUsername;
    }

    public ArrayList<PlayerRecord> getPlayerRecordList() {
        return playerRecordList;
    }

    public void setPlayerRecordList(ArrayList<PlayerRecord> playerRecordList) {
        this.playerRecordList = playerRecordList;
    }
}
