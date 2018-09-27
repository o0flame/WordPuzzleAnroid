package edu.gatech.seclass.sdpscramble.model;

import java.net.SocketTimeoutException;

import edu.gatech.seclass.utilities.ExternalWebService;
/**
 * Created by panpan on 10/9/17.
 */

public class Player {

    private String firstName;
    private String lastName;
    private String desiredUsername;
    private String uniqueUsername;
    private String email;
    private int numberofScrambleSolved;
    private int numberofScrambleCreated;
    private int averageNumberOfTimesScrambleSolvedByOthers;

    public void enterFirstName(String fn) {
        firstName = fn;
        return;
    }


    public void enterLastName(String ln) {
        lastName = ln;
        return;
    }

    public void enterDesiredUserName(String dn) {
        desiredUsername = dn;
        return;
    }

    public void enterEmail(String e) {
        email = e;
        return;
    }

    public String receiveUserName(String dU, String fn, String ln, String e) throws SocketTimeoutException {
        ExternalWebService ews = ExternalWebService.getInstance();
        uniqueUsername = ews.newPlayerService(desiredUsername, firstName, lastName, email);
        return uniqueUsername;

    }

//    public void saveInformation() {
//
//    }

    public Scramble createScramble() {
        boolean isCreated = false;
        Scramble scramble = new Scramble();
        return scramble;
    }

//    public Scramble chooseScramble(UnSolvedScrambleList l) {
//
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesiredUsername() {
        return desiredUsername;
    }

    public void setDesiredUsername(String desiredUsername) {
        this.desiredUsername = desiredUsername;
    }

    public String getUniqueUsername() {
        return uniqueUsername;
    }

    public void setUniqueUsername(String uniqueUsername) {
        this.uniqueUsername = uniqueUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberofScrambleSolved() {
        return numberofScrambleSolved;
    }

    public void setNumberofScrambleSolved(int numberofScrambleSolved) {
        this.numberofScrambleSolved = numberofScrambleSolved;
    }

    public int getNumberofScrambleCreated() {
        return numberofScrambleCreated;
    }

    public void setNumberofScrambleCreated(int numberofScrambleCreated) {
        this.numberofScrambleCreated = numberofScrambleCreated;
    }

    public int getAverageNumberOfTimesScrambleSolvedByOthers() {
        return averageNumberOfTimesScrambleSolvedByOthers;
    }

    public void setAverageNumberOfTimesScrambleSolvedByOthers(int averageNumberOfTimesScrambleSolvedByOthers) {
        this.averageNumberOfTimesScrambleSolvedByOthers = averageNumberOfTimesScrambleSolvedByOthers;
    }
}
