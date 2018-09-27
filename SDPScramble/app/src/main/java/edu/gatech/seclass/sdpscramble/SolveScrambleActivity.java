package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.sdpscramble.model.InProgressScramble;
import edu.gatech.seclass.sdpscramble.model.SolveScramble;
import edu.gatech.seclass.sdpscramble.model.User;
import edu.gatech.seclass.sdpscramble.sql.DatabaseHelper;

import static edu.gatech.seclass.sdpscramble.model.User.inProgressScrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.scrambleList;
import static edu.gatech.seclass.sdpscramble.model.User.username;

public class SolveScrambleActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = SolveScrambleActivity.this;

    Button bCreateS, bChooseS, bViewSStat, bViewPStat, bLogout, bBack;
    Button bExit, bSubmit;
    EditText etPhrase;
    TextView tvClue;
    TextView tvScramble;
    String scrambleID;
    SolveScramble ss = new SolveScramble();
    Boolean inProgress;
    DatabaseHelper databaseHelper = new DatabaseHelper(activity);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_scramble);
        etPhrase = (EditText) findViewById(R.id.etAnswer);
        tvClue = (TextView) findViewById(R.id.tvClue);
        tvScramble = (TextView) findViewById(R.id.tvScramble);


        String tmp = getIntent().getExtras().getString("scrambleInfo");
        int i = 0;
        while (i < tmp.length()) {
            if (tmp.charAt(i) == ',') {
                scrambleID = tmp.substring(0,i);
                break;
            }
            i++;
        }

        User user = new User();
        inProgress = user.checkInProgress(scrambleID,username);
        if(inProgress) {

            etPhrase.setText(inProgressScrambleList.get(username).get(scrambleID).getInputWord());
        }

        tvScramble.setText(scrambleList.get(scrambleID).getPhraseScrambled());
        tvClue.setText(scrambleList.get(scrambleID).getClue());


        etPhrase = (EditText) findViewById(R.id.etAnswer);

        bCreateS = (Button) findViewById(R.id.bCreateS);
        bCreateS.setOnClickListener(this);
        bChooseS = (Button) findViewById(R.id.bChooseS);
        bChooseS.setOnClickListener(this);
        bViewSStat = (Button) findViewById(R.id.bViewSStat);
        bViewSStat.setOnClickListener(this);
        bViewPStat = (Button) findViewById(R.id.bViewPStat);
        bViewPStat.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
        bExit = (Button) findViewById(R.id.bExit);
        bExit.setOnClickListener(this);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        bSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // add to database
        if(etPhrase.length()!=0) {
            postDataToSQLite();
        }
        switch (v.getId()) {

            case R.id.bLogout:
                ss.checkRes(scrambleID, etPhrase.getText().toString());
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.bBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.bAccept:
                finish();
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
            case R.id.bSubmit:
                boolean res = ss.checkRes(scrambleID, etPhrase.getText().toString());
                if (res) {
                    Toast.makeText(this, "Awesome!! You got it right", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "That's not correct. Please try again", Toast.LENGTH_LONG).show();
                    etPhrase.setText("");
                }
                break;
            case R.id.bExit:
                ss.checkRes(scrambleID, etPhrase.getText().toString());
                finish();
                break;
        }

    }
    private void postDataToSQLite() {
        InProgressScramble s = new InProgressScramble();
        s.setInputWord(etPhrase.getText().toString());
        s.setPlayerID(username);
        s.setUniqueIdentifier(scrambleID);
        databaseHelper.addInProgress(s);
    }
}

