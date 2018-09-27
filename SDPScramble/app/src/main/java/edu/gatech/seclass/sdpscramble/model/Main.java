package edu.gatech.seclass.sdpscramble.model;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.gatech.seclass.utilities.ExternalWebService;

public class Main {

    public static void main(String[] args) throws SocketTimeoutException {
        // Retrieve EWS data
        ExternalWebService ews = ExternalWebService.getInstance();
//        String username = ews.newPlayerService("aa","susan","zhang","aab@1.com");
//        System.out.println(username);

        //pl1 0 username, 1 fn, 2 ln, 3 email, 4 solved
        List<List<String>> pl1 = ews.retrievePlayerListService();
        //sl1 0 sid, 1, word, 2 scramble, 3, clue, 4 creatorID
        List<List<String>> sl1 = ews.retrieveScrambleService();


//        System.out.println(pl1);
//        System.out.println(sl1);
        HashMap<String,SolvedScrambleList > solvedScrambleList = new HashMap<>();
        HashMap<String,HashSet<String> > unsolvedScrambleList = new HashMap<>();
        HashMap<String,InProgressScrambleList> inProgressScrambleList = new HashMap<>();
        HashMap<String,HashSet<String> > createdScrambleList = new HashMap<>();
        HashMap<String, Integer> scramblebesolved = new HashMap<>();


        HashMap<String, Player> playerList = new HashMap<>();
        HashMap<String, Scramble> scrambleList = new HashMap<>();
        for (int i = 0; i< pl1.size();i++) {
            String playerID = pl1.get(i).get(0);
            Player player = new Player();
            player.setUniqueUsername(playerID);
            player.setFirstName(pl1.get(i).get(1));
            player.setLastName(pl1.get(i).get(2));
            player.setEmail(pl1.get(i).get(3));
            player.setNumberofScrambleSolved(pl1.get(i).size()-4);
            player.setAverageNumberOfTimesScrambleSolvedByOthers(0);

            SolvedScrambleList solvedSLST = new SolvedScrambleList();
            solvedSLST.setPlayerID(playerID);
            HashSet<String> sslst = new HashSet<>();
            for(int j = 4; j <pl1.get(i).size();j++) {
                sslst.add(pl1.get(i).get(j));
                //Count the number of times solved for each scrambleID
                // not the first time to have the scramble Id, count++

                if(scramblebesolved.containsKey(pl1.get(i).get(j))) {
                    scramblebesolved.put(pl1.get(i).get(j), scramblebesolved.get(pl1.get(i).get(j))+1);
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
        for (int i = 0; i< sl1.size();i++) {
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
            if(createdScrambleList.containsKey(sl1.get(i).get(4))) {
                createdScrambleList.put(sl1.get(i).get(4), createdScrambleList.get(sl1.get(i).get(4))).add(scrambleID);

            }
            else {
                HashSet<String> tmpslst = new HashSet<>();
                tmpslst.add(scrambleID);
                createdScrambleList.put(sl1.get(i).get(4), tmpslst);
            }
            //ScrambleList value: 0 sID, 1 word, 2 Scramble, 3 clue, 4 creatorID, 5 #solved
            scrambleList.put(scrambleID, scramble);
        }


        //playerList #Created
        for(String key:createdScrambleList.keySet()) {

            playerList.get(key).setNumberofScrambleCreated(createdScrambleList.get(key).size());
            int total = 0;
            int divider = playerList.get(key).getNumberofScrambleCreated();
            for(String scrambleID: createdScrambleList.get(key)) {
                // if solved by the creator
                if(solvedScrambleList.get(key).getSolvedScrambleList().contains(scrambleID)) {
                    total += scrambleList.get(scrambleID).getNumberOfTimesSolved()-1;
                }
                else {
                    total += scrambleList.get(scrambleID).getNumberOfTimesSolved();
                }
            }
            playerList.get(key).setAverageNumberOfTimesScrambleSolvedByOthers(total/divider);
        }


        String curPlayerID;
        // you have all the playerList and ScrambleList.
        //create user and login
        String Username = "newuser";
        if(!playerList.containsKey(Username)) {
            //link all the parameters, go to create player activity
            Player p = new Player();
            p.setEmail("zz@a.com");
            p.setFirstName("a");
            p.setLastName("b");
            p.setDesiredUsername(Username);
            p.setAverageNumberOfTimesScrambleSolvedByOthers(0);
            p.setNumberofScrambleCreated(0);
            p.setNumberofScrambleSolved(0);
            curPlayerID = ews.newPlayerService(p.getDesiredUsername(), p.getFirstName(), p.getLastName(),p.getEmail());
            p.setUniqueUsername(curPlayerID);
            playerList.put(curPlayerID, p);
            //login to the accountview
        }
        else {
            //login to the account view
            curPlayerID = Username;
        }


        //create new Scramble:
        //link all the parameters
        Scramble s = new Scramble();
        s.setPhraseNotScrambled("world");
        s.setClue("hello");
        s.setPhraseScrambled("dlrow");
        s.setCreatorID(curPlayerID);

        String newSID = ews.newScrambleService(s.getPhraseNotScrambled(), s.getPhraseScrambled(),s.getClue(),s.getCreatorID());
        s.setScrambleIdentifier(newSID);
        s.setNumberOfTimesSolved(0);
        scrambleList.put(newSID, s);
        //update createdScrambleList
        if (createdScrambleList.containsKey(curPlayerID)) {
            HashSet<String> tmpc = new HashSet<>();
            tmpc =createdScrambleList.get(curPlayerID);
            tmpc.add(newSID);
            createdScrambleList.put(curPlayerID,tmpc);
        } else {
            HashSet<String> t = new HashSet<>();
            t.add(newSID);
            createdScrambleList.put(curPlayerID, t);
        }

        //Solve a Scramble
        boolean update = false;
        String t= "inputWord";
        //check match
        HashSet<InProgressScramble> ips = new HashSet<>();
        if(ews.reportSolveService(newSID,curPlayerID)) {
            update = true;
        } else {
            InProgressScramble tmp = new InProgressScramble();
            tmp.setPlayerID(curPlayerID);
            tmp.setUniqueIdentifier(newSID);
            tmp.setInputWord(t);
            ips.add(tmp);

        }
        InProgressScrambleList n = new InProgressScrambleList();

        inProgressScrambleList.put(curPlayerID,n);


        HashMap<String, PlayerRecord> pr = new HashMap<>();
        ArrayList<PlayerRecord> prL = new ArrayList<>();
        //view scramble stat
        for(String pid: playerList.keySet()) {
            PlayerRecord tmp = new PlayerRecord();
            tmp.setUniqueUsername(playerList.get(pid).getUniqueUsername());
            tmp.setPlayerFirstName(playerList.get(pid).getFirstName());
            tmp.setPlayerLastName(playerList.get(pid).getLastName());
            tmp.setNumberOfScrambleCreated(playerList.get(pid).getNumberofScrambleCreated());
            tmp.setNumberOfScrambleSolved(playerList.get(pid).getNumberofScrambleSolved());
            tmp.setAverageNumberOfTimesScrambleSolvedByOthers(playerList.get(pid).getAverageNumberOfTimesScrambleSolvedByOthers());
            pr.put(pid,tmp);
            prL.add(tmp);
        }
        PlayerStatistics tmpps = new PlayerStatistics();
        tmpps.setUniqueUsername(curPlayerID);
        tmpps.setPlayerRecordList(prL);
        System.out.println("View Player stat");
        System.out.println(prL);

        //view Scramble Stat
        HashMap<String, ScrambleRecord> sr = new HashMap<>();
        ArrayList<ScrambleRecord> srL = new ArrayList<>();

        for(String sid: scrambleList.keySet()) {
            ScrambleRecord tmp = new ScrambleRecord();
            tmp.setUniqueIdentifier(sid);
            tmp.setPlayerID(curPlayerID);
            tmp.setCreator(scrambleList.get(sid).getCreatorID().equals(curPlayerID));
            tmp.setNumberofTimesSolved(scrambleList.get(sid).getNumberOfTimesSolved());
            if(playerList.get(curPlayerID).getNumberofScrambleSolved()==0) {
                tmp.setSolved(false);
            }
            else {
                tmp.setSolved(solvedScrambleList.get(curPlayerID).getSolvedScrambleList().contains(sid));
            }
            tmp.setInProgress(inProgressScrambleList.containsKey(curPlayerID));
            srL.add(tmp);
        }
        ScrambleStatistics tmpss = new ScrambleStatistics();
        tmpss.setPlayerID(curPlayerID);
        tmpss.setScrambleRecordList(srL);
//        System.out.println("view scramble stat");
//        for (int i = 0; i < srL.size(); i++) {
//            System.out.println(srL.get(i).getUniqueIdentifier());
//            System.out.println(srL.get(i).getPlayerID());
//            System.out.println(srL.get(i).getNumberofTimesSolved());
//        }
//        System.out.println(srL);


        //view player stat


        //Add new Player
        //Add new Scramble

//        //InProgressScramble
//
//        InProgressScramble tmp = new InProgressScramble();
//        tmp.setInputWord("hello");
//        tmp.setUniqueIdentifier("Scramble0");
//        tmp.setPlayerID(curPlayerID);
//
//        InProgressScrambleList abc = new InProgressScrambleList();
//        HashSet<String> abcn = new HashSet<>();
//        abcn.add(tmp.getUniqueIdentifier());
//        abc.setPlayerID(curPlayerID);
//        abc.setInProgressScrambleList(abcn);







    }
}
