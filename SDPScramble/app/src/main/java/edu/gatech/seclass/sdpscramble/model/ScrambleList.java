package edu.gatech.seclass.sdpscramble.model;

import java.util.ArrayList;

/**
 * Created by panpan on 10/10/17.
 */

public class ScrambleList {
    private ArrayList<Scramble> scrambleList = new ArrayList<>();

    public ArrayList<Scramble> getScrambleList() {
        return scrambleList;
    }

    public void setScrambleList(ArrayList<Scramble> scrambleList) {
        this.scrambleList = scrambleList;
    }
}
