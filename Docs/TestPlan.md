# Test Plan


**Author**: Feng Xiao, Team 15

## 1 Testing Strategy

### 1.1 Overall strategy

Integration tests are to be used for each user cases. Unit test will be used as supplemental method for integration test. Overall goal for testing is to make sure every test case pass. Regression-testing might be used for further edition and update on an established version.

### 1.2 Test Selection

White-box and black-box testing are to be integrated together for testing. Specifically, black-box testing is used for testing the functionality of codes in this project, use case testing, equivalence class partitioning and boundary value testing are to be used as techniques in black-box testing. On the other side, by using unit testing, statement coverage and branch coverage techniques in white-box testing will ensure the functionality and validity of codes.

### 1.3 Adequacy Criterion

All of use case tests should pass, and tests should have at least 90% code coverage in structure and 90% of all scenarios with respect to requirements in the instruction. For unit testing level, the correctness of pairs between input and output will be ensured, whereas at system level, each functionality of our system will be tested and integrated.


### 1.4 Bug Tracking


All versions of our codes will be pushed to GitHub team REPO, issue tracking system in GitHub will be used for bug tracking. With different branches, the resolution to a specific bug will create a new branch for solving this bug.

### 1.5 Technology

Test technology: Junit test, Espresso <br />
Development Environment: Android Studio <br /> API 26
Emulator: Nexus 5

## 2 Test Cases


## updated 10/19/2017: Error messages are removed, Sing up button is only enabled after entering every input required. In creating scramble, viewscramble button is only enabled for creating, re-scramble button is only enabled after viewscramble. Moreover, multiple users test were added. Finally, multiple-users scenarios are considered.
## for Espresso testing, UI interfaces were checked and Inprogress function was checked, moreover, the record of player and scrambles were also tested. 


| Test Case # | Purpose | Steps to Complete | Expected Result| Actual Result | Pass/Fail |Notes|
|:-------------:|:-----------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|:------------------------------------------------------------------------------------------:|:-------------:|
| 1.0 | Verify main components for login| Open the app | Starting UI displays correctly| show Log In UI | PASS |NA
| 1.1 | Verify main components for new player| create new user by clicking register button| go to the interface to enter user detailed information| jump to sign up activity | PASS  |NA
| 1.2 | Verify main components for new player| enter user detailed information | record information of user, and return players with four game options|   info can be entered and stored | PASS  |NA
| 1.3 | Verify main components for login| enter username and request to login | use a registered username to log in, retrieve information and return to four game options as a player | user info were stored and jump to main activity | PASS |NA
| 1.4 | Verify main components for new player| enter first name, last name, desired username, but without email |Error Message: information missing or not able to click sign up | sign up button is grey when information missing |  PASS|NA
| 1.5 | Verify main components for new player| enter first name, desired username, but without email or last name |Error Message: information missing or not able to click sign up| sign up button is grey when information missing |  PASS|NA
| 2.0 | Verify four gaming options for individual player| after retrieve player information | display four game options on UI:  (1) create a word scramble, (2) choose and solve word scrambles, (3) see statistics on their created and solved word scrambles, and (4) view the player statistics.|  show UI correctly | PASS  |NA
| 2.1 | Verify four gaming options for individual player-create scramble| choose create scramble | display create scramble UI| jump to createScrambleActivity | PASS |NA
| 2.2.0 | Verify four gaming options for individual player-create scramble| choose create scramble, enter a valid phrase, clue | display options: view, re-scramble, save| show UI correctly | PASS  |NA
|2.2.1| Verify four gaming options for individual player-create scramble| enter an invalid phrase | Error Message: information missing| shows correct error message | PASS |NA
|2.2.2| Verify four gaming options for individual player-create scramble| enter an invalid clue | Error Message: information missing|  shows correct error message | PASS |NA
| 2.3 | Verify four gaming options for individual player-create scramble| choose  view | display created scramble| By viewscramble button | PASS |NA
| 2.4 | Verify four gaming options for individual player-create scramble| choose re-scramble | dumb this scramble and create a new one| jump to createNewScramble activity | PASS |NA
| 2.5 | Verify four gaming options for individual player-create scramble| choose save | record this scramble with ID and this scramble cannot be edited| jumped to mainActivity and stored data |PASS  |NA
| 2.6 | Verify four gaming options for individual player-create scramble| enter no letters or empty string in phrase or clue| Error: information missing| shows correct error message | PASS |NA
| 2.7 | Verify four gaming options for individual player-solve scramble| select choose and solve word scramble| display UI for a list of unsolved scramble| showed correct UI   | PASS |NA
| 2.8 | Verify four gaming options for individual player-solve scramble| choose one scramble from the list| display UI for solving chosen scramble| showed correct UI | PASS |NA
| 2.9 | Verify four gaming options for individual player-solve scramble| list has no scrambles| show hint and link to create scramble| showed correct UI |PASS  |NA
| 2.10 | Verify four gaming options for individual player-solve scramble| enter letters and submit solution|  if their answer is correct, return to list, else return to puzzle| return by action meets requirement | PASS |NA
| 2.11 | Verify four gaming options for individual player-solve scramble| user choose exit during solving one scramble|   current state of scramble will be preserved| Current State is preserved | PASS |NA
| 2.12 | Verify four gaming options for individual player-solve scramble| Solution is empty or wrong| Notification Message: That's not correct, please try again| shows correct message  | PASS |NA
| 2.13 | Verify four gaming options for individual player-see statistics| choose see statistics with records| display information of created and solved scrambles by their own| showed correct UI | PASS |NA
| 2.14 | Verify four gaming options for individual player-see statistics| choose see statistics with no record on this player| display information of no record| App has records from initialization | Fail | EWS has few players at initialization
| 2.15 | Verify four gaming options for individual player-view player statistics| choose view player statistics| display player list with their information on scrambles| displayed player information on scramble | PASS |NA
| 3.0 | Verify multiple users| log in with different username| display player list respectively with their information on scrambles| displayed player information on scramble | PASS |NA
| 3.1 | Verify multiple users with same username| log in with different username| distinguish players in| username was added by postfix of digits | PASS |EWS added a postfix to username
| 3.2 | Verify multiple logins with same User| log in with same username| show consistent player's record| display player's record correctly | PASS |NA
