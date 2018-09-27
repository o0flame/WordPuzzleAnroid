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

public class ViewPlayerStatActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player_stat);
        ListView lst = (ListView) findViewById(R.id.listV);

        User user = new User();
        String[] cslist = user.ViewPlayerStat(username);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cslist);

        lst.setAdapter(adapter);
        Button bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(this);
        Button bCreateS = (Button) findViewById(R.id.bCreateS);
        bCreateS.setOnClickListener(this);
        Button bChooseS = (Button) findViewById(R.id.bChooseS);
        bChooseS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bBack:
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