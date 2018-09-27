package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static edu.gatech.seclass.sdpscramble.model.User.playerList;
import static edu.gatech.seclass.sdpscramble.model.User.username;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bCreateS = (Button) findViewById(R.id.bCreateS);
        bCreateS.setOnClickListener(this);
        Button bChooseS = (Button) findViewById(R.id.bChooseS);
        bChooseS.setOnClickListener(this);
        Button bViewSStat = (Button) findViewById(R.id.bViewSStat);
        bViewSStat.setOnClickListener(this);

        Button bViewPStat = (Button) findViewById(R.id.bViewPStat);
        bViewPStat.setOnClickListener(this);
        Button bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeViews();
    }

    private void initializeViews() {
        TextView title = (TextView) findViewById(R.id.title);
        TextView playerID = (TextView) findViewById(R.id.username);

        TextView tSolved = (TextView) findViewById(R.id.tsolved);
        TextView tCreated = (TextView) findViewById(R.id.tcreated);
        TextView average = (TextView) findViewById(R.id.average);

        String message1 = "Welcome " + playerList.get(username).getFirstName() + " " + playerList.get(username).getLastName() + " (" + username + ")";
        title.setText(message1);
        playerID.setText(username);


        String message2 = "# of Scrambled Solved: " + playerList.get(username).getNumberofScrambleSolved();
        tSolved.setText(message2);

        String message3 = "# of Scrambled Created: " + playerList.get(username).getNumberofScrambleCreated();
        tCreated.setText(message3);

        String message4 = "Average # of Times Scrambles Solved by Others: " + playerList.get(username).getAverageNumberOfTimesScrambleSolvedByOthers();
        average.setText(message4);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bLogout:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.bCreateS:
                startActivity(new Intent(this, CreateScrambleActivity.class));
                break;

            case R.id.bChooseS:
                startActivity(new Intent(this, ChooseScrambleActivity.class));
                break;

            case R.id.bViewSStat:
                startActivity(new Intent(this, ViewScrambleStatActivity.class));
                break;

            case R.id.bViewPStat:
                startActivity(new Intent(this, ViewPlayerStatActivity.class));
                break;
        }
    }
}
