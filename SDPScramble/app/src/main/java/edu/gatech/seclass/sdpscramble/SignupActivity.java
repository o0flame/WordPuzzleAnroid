package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.SocketTimeoutException;

import edu.gatech.seclass.sdpscramble.model.Player;
import edu.gatech.seclass.sdpscramble.model.User;

import static edu.gatech.seclass.sdpscramble.R.id.bSave;
import static edu.gatech.seclass.sdpscramble.model.User.username;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etDUsername, etEmail;
    private static final String CONSTRAINT_VALID_FNAME = "first_name";
    private static final String CONSTRAINT_VALID_LNAME = "last_name";
    private static final String CONSTRAINT_VALID_DUNAME = "desired_uname";
    private static final String CONSTRAINT_VALID_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Button bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(this);
        bSave.setEnabled(false);

        final ConstraintResolver constraintResolver = new ConstraintResolver();
        constraintResolver.addConstraint(CONSTRAINT_VALID_DUNAME);
        constraintResolver.addConstraint(CONSTRAINT_VALID_EMAIL);
        constraintResolver.addConstraint(CONSTRAINT_VALID_FNAME);
        constraintResolver.addConstraint(CONSTRAINT_VALID_LNAME);
        constraintResolver.addConstraintResolverCallback(new ConstraintResolver.ConstraintResolverCallback() {
            @Override
            public void onConstraintsAreMet() {
                bSave.setEnabled(true);
            }

            @Override
            public void onConstraintsAreNotMet() {
                bSave.setEnabled(false);
            }
        });

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etFirstName.addTextChangedListener(new MinTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                constraintResolver.changeConstraint(CONSTRAINT_VALID_FNAME, s != null && s.length() > 0);
            }
        });

        etLastName = (EditText) findViewById(R.id.etLastName);
        etLastName.addTextChangedListener(new MinTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                constraintResolver.changeConstraint(CONSTRAINT_VALID_LNAME, s != null && s.length() > 0);
            }
        });

        etDUsername = (EditText) findViewById(R.id.etDUsername);
        etDUsername.addTextChangedListener(new MinTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                constraintResolver.changeConstraint(CONSTRAINT_VALID_DUNAME, s != null && s.length() > 0);
            }
        });

        etEmail = (EditText) findViewById(R.id.etEmail);
        etEmail.addTextChangedListener(new MinTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                constraintResolver.changeConstraint(CONSTRAINT_VALID_EMAIL, s != null && s.length() > 0);
            }
        });

        Button bCancel = (Button) findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);


    }

    private static class MinTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Do Nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Do Nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            //Do Nothing
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case bSave:
                //show error msg
                if(etFirstName.length()==0){
                    etFirstName.setError("information missing");
                }
                if(etDUsername.length()==0){
                    etDUsername.setError("information missing");
                }
                if(etEmail.length()==0){
                    etEmail.setError("information missing");
                }
                if(etLastName.length()==0){
                    etLastName.setError("information missing");
                }


                // create a new player and save it to EWS
                Player player = new Player();
                User user = new User();
                String curID = "";
                player.setDesiredUsername(etDUsername.getText().toString());
                player.setFirstName(etFirstName.getText().toString());
                player.setLastName(etLastName.getText().toString());
                player.setEmail(etEmail.getText().toString());
                player.setNumberofScrambleCreated(0);
                player.setNumberofScrambleSolved(0);
                player.setAverageNumberOfTimesScrambleSolvedByOthers(0);
                try {
                    curID = user.createNewPlayer(player);
                } catch (SocketTimeoutException e) {
                    e.printStackTrace();
                }
                // Please check whetehr curID is valid
                player.setUniqueUsername(curID);
                username = curID;

                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

            case R.id.bCancel:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }

    }
}
