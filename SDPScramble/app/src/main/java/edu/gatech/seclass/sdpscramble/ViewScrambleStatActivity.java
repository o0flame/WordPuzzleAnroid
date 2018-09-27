package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.gatech.seclass.sdpscramble.model.User;

import static edu.gatech.seclass.sdpscramble.model.User.username;


public class ViewScrambleStatActivity extends AppCompatActivity implements View.OnClickListener {

    String[] cslist;
    ListView lst;
    Button bCreateS, bChooseS, bBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scramble_stat);

        lst = (ListView) findViewById(R.id.listV);

        User user = new User();
        cslist = user.ViewScrambleStat(username);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cslist);


        lst.setAdapter(adapter);
        bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
        bCreateS = (Button) findViewById(R.id.bCreateS);
        bCreateS.setOnClickListener(this);
        bChooseS = (Button) findViewById(R.id.bChooseS);
        bChooseS.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;

            case R.id.bCreateS:
                startActivity(new Intent(this, CreateScrambleActivity.class));
                break;

            case R.id.bChooseS:
                startActivity(new Intent(this, ChooseScrambleActivity.class));
                break;
        }

    }

}