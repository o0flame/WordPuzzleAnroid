package edu.gatech.seclass.sdpscramble.model;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.gatech.seclass.utilities.ExternalWebService;

/**
 * Created by panpan on 10/9/17.
 */

public class User {
    public static String username;
    private static List<List<String>> pl1;
    private static List<List<String>> sl1;
    public static HashMap<String, Player> playerList = new HashMap<>();
    public static HashMap<String, Scramble> scrambleList = new HashMap<>();
    public static HashMap<String, HashSet<String>> createdScrambleList = new HashMap<>();
    public static HashMap<String, SolvedScrambleList> solvedScrambleList = new HashMap<>();
    public static HashMap<String, HashMap<String, InProgressScramble>> inProgressScrambleList = new HashMap<>();
    public static HashMap<String, Integer> scramblebesolved = new HashMap<>();

    /**
     * This is used to created new player, with the player Id returned from ews.
     * @param p
     * @return
     * @throws SocketTimeoutException
     */
    public String createNewPlayer(Player p) throws SocketTimeoutException {
        ExternalWebService ews = ExternalWebService.getInstance();
        String curPlayerID;
        // you have all the playerList and ScrambleList.
        //create user and login

        curPlayerID = ews.newPlayerService(p.getDesiredUsername(), p.getFirstName(), p.getLastName(), p.getEmail());
        p.setUniqueUsername(curPlayerID);
        playerList.put(curPlayerID, p);
        return curPlayerID;
    }

    /**
     * This is used to get all data from ews and check if the username exists. If so, login.
     * if not, go to the signup activity
     * @param username
     * @return
     * @throws SocketTimeoutException
     */
    public boolean checkUsername(String username) throws SocketTimeoutException {
        // Retrieve EWS data

        ExternalWebService ews = ExternalWebService.getInstance();
        //        String username = ews.newPlayerService("aa","susan","zhang","aab@1.com");
        //        System.out.println(username);

        //pl1 0 username, 1 fn, 2 ln, 3 email, 4 solved
        pl1 = ews.retrievePlayerListService();
        //sl1 0 sid, 1, word, 2 scramble, 3, clue, 4 creatorID
        sl1 = ews.retrieveScrambleService();


        for (int i = 0; i < pl1.size(); i++) {
            String playerID = pl1.get(i).get(0);
            Player player = new Player();
            player.setUniqueUsername(playerID);
            player.setFirstName(pl1.get(i).get(1));
            player.setLastName(pl1.get(i).get(2));
            player.setEmail(pl1.get(i).get(3));
            player.setNumberofScrambleSolved(pl1.get(i).size() - 4);
            player.setAverageNumberOfTimesScrambleSolvedByOthers(0);

            SolvedScrambleList solvedSLST = new SolvedScrambleList();
            solvedSLST.setPlayerID(playerID);
            HashSet<String> sslst = new HashSet<>();
            for (int j = 4; j < pl1.get(i).size(); j++) {
                sslst.add(pl1.get(i).get(j));
                //Count the number of times solved for each scrambleID
                // not the first time to have the scramble Id, count++

                if (scramblebesolved.containsKey(pl1.get(i).get(j))) {
                    scramblebesolved.put(pl1.get(i).get(j), scramblebesolved.get(pl1.get(i).get(j)) + 1);
                }
                //the first time the scramble id shows up
                else {
                    scramblebesolved.put(pl1.get(i).get(j), 1);
                }

            }
            solvedSLST.setSolvedScrambleList(sslst);

            solvedScrambleList.put(playerID, solvedSLST);

            //playerList value: 0 pID, 1 FirtName, 2 LastName, 3 email,4 #solved, 5 #created, 6 #avg sovled 7. desiredUsername

            playerList.put(pl1.get(i).get(0), player);
        }
        for (int i = 0; i < sl1.size(); i++) {
            String scrambleID = sl1.get(i).get(0);
            Scramble scramble = new Scramble();
            scramble.setScrambleIdentifier(scrambleID);
            scramble.setPhraseNotScrambled(sl1.get(i).get(1));
            scramble.setPhraseScrambled(sl1.get(i).get(2));
            scramble.setClue(sl1.get(i).get(3));
            scramble.setCreatorID(sl1.get(i).get(4));
            if (scramblebesolved.containsKey(scrambleID)) {
                scramble.setNumberOfTimesSolved(scramblebesolved.get(scrambleID));
            } else {
                scramble.setNumberOfTimesSolved(0);
            }
            // create createdScrambleList
            if (createdScrambleList.containsKey(sl1.get(i).get(4))) {
                createdScrambleList.put(sl1.get(i).get(4), createdScrambleList.get(sl1.get(i).get(4))).add(scrambleID);

            } else {
                HashSet<String> tmpslst = new HashSet<>();
                tmpslst.add(scrambleID);
                createdScrambleList.put(sl1.get(i).get(4), tmpslst);
            }
            //ScrambleList value: 0 sID, 1 word, 2 Scramble, 3 clue, 4 creatorID, 5 #solved
            scrambleList.put(scrambleID, scramble);
        }


        //playerList #Created
        for (String key : createdScrambleList.keySet()) {

            playerList.get(key).setNumberofScrambleCreated(createdScrambleList.get(key).size());
            int total = 0;
            int divider = playerList.get(key).getNumberofScrambleCreated();
            for (String scrambleID : createdScrambleList.get(key)) {
                // if solved by the creator
                if (solvedScrambleList.get(key).getSolvedScrambleList().contains(scrambleID)) {
                    total += scrambleList.get(scrambleID).getNumberOfTimesSolved() - 1;
                } else {
                    total += scrambleList.get(scrambleID).getNumberOfTimesSolved();
                }
            }
            playerList.get(key).setAverageNumberOfTimesScrambleSolvedByOthers(total / divider);
        }



        return playerList.containsKey(username);
    }

    /**
     * This is used to generated the unsolved list for selections. The InProgress Scrambles will be listed first.
     * @return
     */


    public String[] GenerateChooseScrambleList() {
        List<String> cslist = new ArrayList<>();
        HashSet<String> unsolv = new HashSet<>();

        //get unsolved scramble first
        if (playerList.get(username).getNumberofScrambleSolved() == 0) {
            for (String key : scrambleList.keySet()) {
                unsolv.add(key);
            }
        } else {
            for (String key : scrambleList.keySet()) {

                HashSet kk = solvedScrambleList.get(username).getSolvedScrambleList();
                if (!kk.contains(key)) {
                    unsolv.add(key);
                }
            }

        }

        //add in Progress Scrambles first
        for (String key: unsolv) {
            if (inProgressScrambleList.containsKey(username)) {
                if (inProgressScrambleList.get(username).containsKey(key)) {

                    cslist.add(key+", created by  " +scrambleList.get(key).getCreatorID()+", "+"Y");
                }
            } else {
                break;
            }

        }

        for (String key : unsolv) {
            if (!inProgressScrambleList.containsKey(username) || !inProgressScrambleList.get(username).containsKey(key)) {
                String tmp3;
                String creator;
                String isInprogress;
                Scramble sn = scrambleList.get(key);

                isInprogress = "N";

                tmp3 = key + ", created by: " + sn.getCreatorID() + " , " + isInprogress;
                cslist.add(tmp3);
            }

        }
        String [] res = new String [cslist.size()];
        int i = 0;
        for(String each: cslist) {
            res[i++] = each;
        }
        return res;

    }


    private static class ScrambleStat {
        private String stats;
        private int solved;

        public ScrambleStat(String stats, int solved) {
            this.stats = stats;
            this.solved = solved;
        }
    }

    public String[] ViewScrambleStat(String playerID) {

        List<ScrambleStat> list = new ArrayList<>();
        for (String key : scrambleList.keySet()) {
            String tmp = key + ",  ";
            String isSolved;
            String isCreator;
            String isInProgress;

            int cnt = scrambleList.get(key).getNumberOfTimesSolved();
            if (solvedScrambleList.containsKey(playerID)) {
                if (solvedScrambleList.get(playerID).getSolvedScrambleList().contains(key)) {
                    isSolved = "Y";
                } else {
                    isSolved = "N";

                }
            } else {
                isSolved = "N";
            }

            if (scrambleList.get(key).getCreatorID().equals(playerID)) {
                isCreator = "Y";
            } else {
                isCreator = "N";
            }
            if (inProgressScrambleList.containsKey(playerID)) {
                if (inProgressScrambleList.get(playerID).containsKey(key)) {
                    isInProgress = "Y";
                } else {
                    isInProgress = "N";
                }
            } else {
                isInProgress = "N";
            }

            tmp += Integer.toString(cnt) + ",  " + isSolved + ",  " + isCreator + ",  " + isInProgress;
            list.add(new ScrambleStat(tmp, cnt));
        }

        Collections.sort(list, new Comparator<ScrambleStat>() {
            @Override
            public int compare(ScrambleStat o1, ScrambleStat o2) {
                return Integer.compare(o2.solved, o1.solved);
            }
        });

        String[] res = new String[list.size()];
        int i = 0;
        for (ScrambleStat each : list) {
            res[i++] = each.stats;
        }
        return res;

    }

    private static class PlayerStats {
        private String playerData;
        private int solved;

        public PlayerStats(String playerData, int solved) {
            this.playerData = playerData;
            this.solved = solved;
        }
    }


    /**
     * This is used to view Scramble Statistics, sorted by the decreasing of total times scramble being solved.
     * @param playerID
     * @return
     */


    public String[] ViewPlayerStat(String playerID) {
        //need to sort by decreased nsolved
        List<PlayerStats> list = new ArrayList<>();
        for (String key : playerList.keySet()) {
            String tmp;
            String name;
            int solved;
            String nsolved;
            String ncreated;
            String average;
            name = playerList.get(key).getFirstName() + " " + playerList.get(key).getLastName();
            solved = playerList.get(key).getNumberofScrambleSolved();
            nsolved = Integer.toString(solved);
            ncreated = Integer.toString(playerList.get(key).getNumberofScrambleCreated());
            average = Integer.toString(playerList.get(key).getAverageNumberOfTimesScrambleSolvedByOthers());


            tmp = name + ",  " + key + ",  " + nsolved + ",  " + ncreated + ",  " + average;
            list.add(new PlayerStats(tmp, solved));

        }

        Collections.sort(list, new Comparator<PlayerStats>() {
            @Override
            public int compare(PlayerStats o1, PlayerStats o2) {
                return Integer.compare(o2.solved, o1.solved);
            }
        });
        String[] res = new String[list.size()];
        int i = 0;
        for (PlayerStats each : list) {
            res[i++] = each.playerData;
        }
        return res;

    }

    /**
     * This method check if the playerID has scrambleID as the in progress scramble.
     * @param scrambleID
     * @param playerID
     * @return
     */
    public boolean checkInProgress(String scrambleID, String playerID) {
        if (inProgressScrambleList.containsKey(playerID)) {
            if (inProgressScrambleList.get(playerID).containsKey(scrambleID)) {
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }

    }



}
