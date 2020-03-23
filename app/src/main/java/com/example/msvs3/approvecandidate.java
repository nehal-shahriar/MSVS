package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class approvecandidate extends AppCompatActivity {
    TextView appcandidatenametxt,appcandidatecgtxt,appcandidatedepttxt,appcandidateprotxt;
    ImageView candidateImageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approvecandidate);
        appcandidatenametxt = findViewById(R.id.appcandidatenametxt);
        appcandidatecgtxt = findViewById(R.id.appcandidatecgtxt);
        appcandidatedepttxt = findViewById(R.id.appcandidatedepttxt);
        appcandidateprotxt = findViewById(R.id.appcandidateprotxt);
        candidateImageview = findViewById(R.id.candidateimageView);
        String teama ="Name: "+ getIntent().getStringExtra("name");
        String teamb ="Cg: "+ getIntent().getStringExtra("cg");
        String teamc = "Department: "+ getIntent().getStringExtra("department");
        String teamd ="Propaganda: "+ getIntent().getStringExtra("propaganda");
        String teame = getIntent().getStringExtra("imgurl");
        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this, "" + teama, Toast.LENGTH_SHORT).show();
        appcandidatenametxt.setText(teama);
        appcandidatecgtxt.setText(teamb);
        appcandidatedepttxt.setText(teamc);
        appcandidateprotxt.setText(teamd);
        Picasso.get().load(teame).into(candidateImageview);
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

