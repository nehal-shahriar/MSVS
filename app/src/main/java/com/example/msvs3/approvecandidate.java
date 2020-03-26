package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class approvecandidate extends AppCompatActivity {
    TextView appcandidatenametxt,appcandidatecgtxt,appcandidatedepttxt,appcandidateprotxt,appeventtxt,appposttxt;
    ImageView candidateImageview;
    Button canapprovebtn;
    private DatabaseReference df;
    String teama,teamb,teamc,teamd,teame,teamf,teamg,teamh,teami;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecandidate);
        appcandidatenametxt = findViewById(R.id.appcandidatenametxt);
        appcandidatecgtxt = findViewById(R.id.appcandidatecgtxt);
        appcandidatedepttxt = findViewById(R.id.appcandidatedepttxt);
        appcandidateprotxt = findViewById(R.id.appcandidateprotxt);
        candidateImageview = findViewById(R.id.candidateimageView);
        appeventtxt=findViewById(R.id.appeventtxt);
        appposttxt=findViewById(R.id.appposttxt);
        canapprovebtn=findViewById(R.id.canapprovebtn);
        teama =getIntent().getStringExtra("name");
        teamb =getIntent().getStringExtra("cg");
        teamc = getIntent().getStringExtra("department");
        teamd =getIntent().getStringExtra("propaganda");
        teame = getIntent().getStringExtra("imgurl");
        teamf =getIntent().getStringExtra("event");
        teamg =getIntent().getStringExtra("post");
        teamh= getIntent().getStringExtra("id");
        teami=getIntent().getStringExtra("level");
        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this, "" + teama, Toast.LENGTH_SHORT).show();
        appcandidatenametxt.setText(teama);
        appcandidatecgtxt.setText(teamb);
        appcandidatedepttxt.setText(teamc);
        appcandidateprotxt.setText(teamd);
        appeventtxt.setText(teamf);
        appposttxt.setText(teamg);
        Picasso.get().load(teame).into(candidateImageview);
        canapprovebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df= FirebaseDatabase.getInstance().getReference().child("Approvedcandidate");
                DataSetFire candi=new DataSetFire();
                candi.setName(teama);
                candi.setId(teamh);
                candi.setDept(teamc);
                candi.setCg(teamb);
                candi.setPropaganda(teamd);
                candi.setImgurl(teame);
                candi.setLevel(teami);
                String x=df.push().getKey();
                candi.setKey(x);
                df.child(teamf).child(teamg).child(x).setValue(candi);
                Toast.makeText(approvecandidate.this,"candidate is approved",Toast.LENGTH_SHORT).show();
            }
        });
        //final Uri image=Uri.parse(teame);
        //candidateImageview.setImageURI(image);
        //new DownloadImageTask(candidateImageview).execute(teame);
    }
        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView bmImage;

            public DownloadImageTask(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
            }
        }

    }

