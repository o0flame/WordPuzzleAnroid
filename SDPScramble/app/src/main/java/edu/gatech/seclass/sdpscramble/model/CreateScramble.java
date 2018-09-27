package edu.gatech.seclass.sdpscramble.model;

import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Random;

import edu.gatech.seclass.utilities.ExternalWebService;

import static edu.gatech.seclass.sdpscramble.model.User.createdScrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.playerList;
import static edu.gatech.seclass.sdpscramble.model.User.scrambleList;

/**
 * Created by panpan on 10/12/17.
 */

public class CreateScramble {
    private boolean isCreated = false;

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public String reScramble(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        int wordStart = 0;
        int wordEnd = -1;
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isAlphabetic(word.charAt(i))) {
                addShuffledWord(stringBuilder, word, wordStart, wordEnd);
                stringBuilder.append(word.charAt(i));
                wordStart = i + 1;
            } else {
                wordEnd = i;
            }
        }

        addShuffledWord(stringBuilder, word, wordStart, wordEnd);
        return stringBuilder.toString();
    }

    private void addShuffledWord(StringBuilder stringBuilder, String word, int wordStart, int wordEnd) {
        if (wordEnd < wordStart || wordStart >= word.length()) return;
        Random random = new Random();
        StringBuilder stringBuilderWord = new StringBuilder(word.substring(wordStart, wordEnd + 1));
        while (stringBuilderWord.length() > 0) {
            int nextIndex = random.nextInt(stringBuilderWord.length());
            stringBuilder.append(stringBuilderWord.charAt(nextIndex));
            stringBuilderWord.deleteCharAt(nextIndex);
        }
    }


    public String acceptResult(Scramble s) throws SocketTimeoutException {
        ExternalWebService ews = ExternalWebService.getInstance();
        String sID;

        sID = ews.newScrambleService(s.getPhraseNotScrambled(), s.getPhraseScrambled(), s.getClue(), s.getCreatorID());
        //Remind: Test if sID is valid
        if (!sID.equals("")) {
            isCreated = true;

            s.setScrambleIdentifier(sID);
            scrambleList.put(sID, s);

            //update createdScrambleList
            if (createdScrambleList.containsKey(s.getCreatorID())) {
                //add current sID to the creator's list
                HashSet<String> tmpc = new HashSet<>();
                tmpc = createdScrambleList.get(s.getCreatorID());
                tmpc.add(sID);
                createdScrambleList.put(s.getCreatorID(), tmpc);
            } else {
                //The creator's first scramble to create
                HashSet<String> t = new HashSet<>();
                t.add(sID);
                createdScrambleList.put(s.getCreatorID(), t);
            }
            //cur player's number of created incresed by 1
            Player p = playerList.get(s.getCreatorID());
            p.setNumberofScrambleCreated(p.getNumberofScrambleCreated() + 1);
        }
        return sID;

    }

}
