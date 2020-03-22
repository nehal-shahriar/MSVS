package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class approvecandidate extends AppCompatActivity {
    TextView appcandidatenametxt,appcandidatecgtxt,appcandidatedepttxt,appcandidateprotxt;
    ImageView candidateImageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecandidate);
        appcandidatenametxt=findViewById(R.id.appcandidatenametxt);
        appcandidatecgtxt=findViewById(R.id.appcandidatecgtxt);
        appcandidatedepttxt=findViewById(R.id.appcandidatedepttxt);
        appcandidateprotxt=findViewById(R.id.appcandidateprotxt);
        candidateImageview=findViewById(R.id.candidateimageView);
        String teama=getIntent().getStringExtra("name");
        String teamb=getIntent().getStringExtra("cg");
        String teamc=getIntent().getStringExtra("dept");
        String teamd=getIntent().getStringExtra("propaganda");
        String teame=getIntent().getStringExtra("image");
        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this,""+teama,Toast.LENGTH_SHORT).show();
        appcandidatenametxt.setText(teama);
        appcandidatecgtxt.setText(teamb);
        appcandidatedepttxt.setText(teamc);
        appcandidateprotxt.setText(teamd);
        final Uri image=Uri.parse(teame);
        candidateImageview.setImageURI(image);
    }
}
