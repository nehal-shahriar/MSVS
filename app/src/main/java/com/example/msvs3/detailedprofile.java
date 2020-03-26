package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class detailedprofile extends AppCompatActivity {
    TextView candidatenametxt,candidateidtxt,candidatedepttxt,candidatecgtxt,candidatelvltxt,candidateprotxt;
    ImageView candidateimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedprofile);
        candidatenametxt=findViewById(R.id.candidatenametxt);
        candidateidtxt=findViewById(R.id.candidateidtxt);
        candidatedepttxt=findViewById(R.id.candidatedepttxt);
        candidatecgtxt=findViewById(R.id.candidatecgtxt);
        candidatelvltxt=findViewById(R.id.candidatelvltxt);
        candidateprotxt=findViewById(R.id.candidateprotxt);
        candidateimage=findViewById(R.id.candidateimage);
        String teama=getIntent().getStringExtra("name");
        String teamb=getIntent().getStringExtra("id");
        String teamc=getIntent().getStringExtra("dept");
        String teamd=getIntent().getStringExtra("cg");
        String teame=getIntent().getStringExtra("lvl");
        String teamf=getIntent().getStringExtra("propaganda");
        String teamg=getIntent().getStringExtra("imgurl");

        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this,""+teama,Toast.LENGTH_SHORT).show();
        candidatenametxt.setText(teama);
        candidateidtxt.setText(teamb);
        candidatedepttxt.setText(teamc);
        candidatecgtxt.setText(teamd);
        candidatelvltxt.setText(teame);
        candidateprotxt.setText(teamf);
        Picasso.get().load(teamg).into(candidateimage);
    }
}
