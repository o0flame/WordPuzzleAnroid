package edu.gatech.seclass.sdpscramble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import edu.gatech.seclass.sdpscramble.model.User;



public class ChooseScrambleActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String [] cslist;
    ListView lst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scramble);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize() {
        lst = (ListView) findViewById(R.id.list);

        User user = new User();
        try {
            user.checkUsername(User.username);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        cslist = user.GenerateChooseScrambleList();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cslist);


        lst.setAdapter(adapter);
        lst.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        Toast.makeText(this, "You click on"+tv.getText()+position,Toast.LENGTH_SHORT).show();
        Intent intent = new  Intent(ChooseScrambleActivity.this, SolveScrambleActivity.class);
        intent.putExtra("scrambleInfo", tv.getText().toString());

        startActivity(intent);
    }

}