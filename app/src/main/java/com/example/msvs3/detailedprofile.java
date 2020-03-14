package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class detailedprofile extends AppCompatActivity {
    TextView candidatenametxt,candidateidtxt,candidatedepttxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedprofile);
        candidatenametxt=findViewById(R.id.candidatenametxt);
        candidateidtxt=findViewById(R.id.candidateidtxt);
        candidatedepttxt=findViewById(R.id.candidatedepttxt);
        String teamone=getIntent().getStringExtra("name");
        String teamtwo=getIntent().getStringExtra("id");
        String teamthree=getIntent().getStringExtra("voteno");
        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this,""+teamone,Toast.LENGTH_SHORT).show();
        candidatenametxt.setText(teamone);
        candidateidtxt.setText(teamtwo);
        candidatedepttxt.setText(teamthree);
    }
}
