package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.SocketTimeoutException;

import edu.gatech.seclass.sdpscramble.model.User;

import static edu.gatech.seclass.sdpscramble.model.User.username;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);

        final Button bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);
        bLogin.setEnabled(false);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bLogin.setEnabled(s != null && s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private boolean init() throws SocketTimeoutException {
        User user = new User();
        return user.checkUsername(etUsername.getText().toString());
    }

    @Override

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bLogin:
                new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Void... params) {
                        boolean status = false;
                        try {
                            status = init();
                        } catch (SocketTimeoutException e) {
                            e.printStackTrace();
                        }
                        return status;
                    }

                    @Override
                    protected void onPostExecute(Boolean status) {
                        super.onPostExecute(status);
                        // Is username exist? No, go to signup UI; Yes, go to Main UI.
                        if (!status) {
                            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                        } else {
                            // if the username exist, login to the existing username.
                            username = etUsername.getText().toString();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("playerID", etUsername.getText().toString());
                            startActivity(intent);
                        }

                        finish();

                    }
                }.execute();
        }
    }
}
