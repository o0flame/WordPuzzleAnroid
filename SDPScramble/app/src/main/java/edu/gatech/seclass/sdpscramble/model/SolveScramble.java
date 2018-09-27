package edu.gatech.seclass.sdpscramble.model;

import java.util.HashMap;
import java.util.HashSet;

import edu.gatech.seclass.utilities.ExternalWebService;

import static edu.gatech.seclass.sdpscramble.model.User.inProgressScrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.playerList;
import static edu.gatech.seclass.sdpscramble.model.User.scrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.solvedScrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.username;

/**
 * Created by panpan on 10/13/17.
 */

public class SolveScramble {
   private boolean isSolved;
    private String inputWord;


    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public String getInputWord() {
        return inputWord;
    }

    public void setInputWord(String inputWord) {
        this.inputWord = inputWord;
    }



    public boolean checkRes(String sID, String ins) {

        // save in progress word or the wrong answer.
        inputWord = ins;
        InProgressScramble tmp = new InProgressScramble();
        tmp.setPlayerID(username);
        tmp.setUniqueIdentifier(sID);
        tmp.setInputWord(inputWord);
        ExternalWebService ews = ExternalWebService.getInstance();
        isSolved = false;
        if (scrambleList.get(sID).getPhraseNotScrambled().equals(inputWord)) {
            //Scramble solved
            isSolved = ews.reportSolveService(sID, username);

            // If this scramble is inProgress before, remove it from the inProgress.

            if (isSolved) {
                if (inProgressScrambleList.containsKey(username) && inProgressScrambleList.get(username).containsKey(sID)) {
                    inProgressScrambleList.get(username).remove(sID);
                }

                int news = playerList.get(username).getNumberofScrambleSolved() + 1;
                playerList.get(username).setNumberofScrambleSolved(news);
                scrambleList.get(sID).setNumberOfTimesSolved(scrambleList.get(sID).getNumberOfTimesSolved() + 1);

                // add the solved scramble to solved scramble list

                if (solvedScrambleList.containsKey(username)) {
                    // add the solved scramble to username
                    SolvedScrambleList sslst = solvedScrambleList.get(username);
                    HashSet<String> hstmp = new HashSet<>();
                    hstmp = sslst.getSolvedScrambleList();
                    hstmp.add(sID);
                    sslst.setSolvedScrambleList(hstmp);
                    solvedScrambleList.put(username, sslst);
                }

            }
        } else {
                //Save the input word if it is not empty
                if (!ins.equals("")) {
                    HashMap<String, InProgressScramble> hmtmp = new HashMap<>();

                    if (inProgressScrambleList.containsKey(username)) {
                        // have this scrambles in progress
                        hmtmp = inProgressScrambleList.get(username);
                        hmtmp.put(sID, tmp);
                        inProgressScrambleList.put(username, hmtmp);

                    } else {
                        // never solve any scramble
                        hmtmp.put(sID, tmp);
                        inProgressScrambleList.put(username, hmtmp);
                    }


                }
            }


        return isSolved;
    }

    }