This is just a temporary file.

Hi, I have made great progress for this project.

Currently I have login and register activities ready. You could test it out buttons.

My User.checkUsername() has many stuffs in the moment (communicate with EWS), I will work on the logic flow and split it out late in the afternoon. The MainActivity UI needs to worked more.


I believe  I could finish most of the coding part, together with the UI connected tonight.


I also modify the local database to install inProgressScrambleList only, since this is the only information that cannot be got from remote Database( EWSmock in this project). I have not done the connection yet, but I feel it should be easy for me. If you have confusion, please leave a message.


BTW, the parameters for each class has changed slightly comparing with UML team design. One things is I use String as the playerID instead of Player type. This is because with playerID, we could retrieve all the player information and it is a waste to have Player type parameters for most of the classes.

You could address this in a doc about what we have changed comparing with previous design.
You could try to think about some test cases and also read my User.checkUsername to understand it better.
