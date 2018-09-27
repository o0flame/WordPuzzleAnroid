package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.SocketTimeoutException;

import edu.gatech.seclass.sdpscramble.model.CreateScramble;
import edu.gatech.seclass.sdpscramble.model.Scramble;

import static edu.gatech.seclass.sdpscramble.model.User.username;

public class CreateScrambleActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = CreateScrambleActivity.this;

    //Rescramble the word, Accept it or back to the main activity.

    Button bRescramble, bViewS, bAccept, bBack, btnGoToAccountView;
    Button bChooseS, bViewSStat, bLogout;
    EditText etPhrase, etClue;
    TextView phraseScrambled;
    CreateScramble cs = new CreateScramble();
    Scramble scramble = new Scramble();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scramble);

        etPhrase = (EditText) findViewById(R.id.etPhrase);
        etPhrase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bRescramble.setEnabled(false);
                bViewS.setEnabled(true);
                bAccept.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etClue = (EditText) findViewById(R.id.etClue);
        phraseScrambled = (TextView) findViewById(R.id.scramble);

        bViewS = (Button) findViewById(R.id.bViewS);
        bViewS.setOnClickListener(this);

        bRescramble = (Button) findViewById(R.id.bRescramble);
        bRescramble.setOnClickListener(this);
        bRescramble.setEnabled(false);

        bAccept = (Button) findViewById(R.id.bAccept);
        bAccept.setOnClickListener(this);
        bAccept.setEnabled(false);

        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);


        bChooseS = (Button) findViewById(R.id.bChooseS);
        bChooseS.setOnClickListener(this);
        bViewSStat = (Button) findViewById(R.id.bViewSStat);
        bViewSStat.setOnClickListener(this);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        btnGoToAccountView = (Button) findViewById(R.id.btn_back_to_account_view);
        btnGoToAccountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateScrambleActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bViewS:
                if(etPhrase.length()==0){
                    etPhrase.setError("information missing");
                }
                if(etClue.length()==0){
                    etClue.setError("information missing");
                }

                //call CreateScramble.rescramble
                if(etPhrase.getText().length() > 0) {
                    phraseScrambled.setText(cs.reScramble(etPhrase.getText().toString()));
                    bRescramble.setEnabled(true);
                    bViewS.setEnabled(false);
                }
                break;

            case R.id.bRescramble:
                //UnSatisfied, call CreateScramble.rescramble again
                phraseScrambled.setText(cs.reScramble(etPhrase.getText().toString()));
                break;
            case R.id.bAccept:
                // accept the result
                scramble.setClue(etClue.getText().toString());
                scramble.setPhraseNotScrambled(etPhrase.getText().toString());
                scramble.setCreatorID(username);
                scramble.setPhraseScrambled(phraseScrambled.getText().toString());
                scramble.setNumberOfTimesSolved(0);

                try {// if success
                    String sID = cs.acceptResult(scramble);
                    if (!sID.equals("")) {

                        //Jump to main activity when the scramble has been successfull created;
                        finish();
                    }

                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.bBack:
                etPhrase.setText("");
                etClue.setText("");
                phraseScrambled.setText("");
                etPhrase.requestFocus();
                break;

            case R.id.bLogout:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;

            case R.id.bChooseS:
                startActivity(new Intent(this, ChooseScrambleActivity.class));
                break;

            case R.id.bViewSStat:
                startActivity(new Intent(this, ViewScrambleStatActivity.class));
                break;
        }

    }
}
