package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

public class detailedprofile extends AppCompatActivity {
    TextView candidatenametxt,candidateidtxt,candidatedepttxt,candidatecgtxt,candidatelvltxt,candidateprotxt;
    ImageView candidateimage;
    String finalname,finalbarcode,finalid,finaldept;
    Button votebtn;
    String teamevent,teampost;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.AuthenticationCallback authenticationCallback;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "detailedprofile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedprofile);
        try {
            Class<?> cls=Class.forName("com.example.msvs3.Profile_of_user");
            Field field=cls.getDeclaredField("barcoderesult");
            Field field1=cls.getDeclaredField("studentname");
            Field field2=cls.getDeclaredField("studentid");
            Field field3=cls.getDeclaredField("studentdept");
            field.setAccessible(true);
            field1.setAccessible(true);
            field2.setAccessible(true);
            field3.setAccessible(true);
            Object object=field.get(new Profile_of_user());
            Object object1=field1.get(new Profile_of_user());
            Object object2=field2.get(new Profile_of_user());
            Object object3=field3.get(new Profile_of_user());
            finalbarcode=(String)object;
            finalname=(String)object1;
            finalid=(String)object2;
            finaldept=(String)object3;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        candidatenametxt=findViewById(R.id.candidatenametxt);
        candidateidtxt=findViewById(R.id.candidateidtxt);
        candidatedepttxt=findViewById(R.id.candidatedepttxt);
        candidatecgtxt=findViewById(R.id.candidatecgtxt);
        candidatelvltxt=findViewById(R.id.candidatelvltxt);
        candidateprotxt=findViewById(R.id.candidateprotxt);
        candidateimage=findViewById(R.id.candidateimage);
        votebtn=findViewById(R.id.votingbtn);
        String teama=getIntent().getStringExtra("name");
        final String teamb=getIntent().getStringExtra("id");
        String teamc=getIntent().getStringExtra("dept");
        String teamd=getIntent().getStringExtra("cg");
        String teame=getIntent().getStringExtra("lvl");
        String teamf=getIntent().getStringExtra("propaganda");
        String teamg=getIntent().getStringExtra("imgurl");
        teamevent=getIntent().getStringExtra("event");
        teampost=getIntent().getStringExtra("post");
        final String teamkey= getIntent().getStringExtra("key");

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

        fingerprintManager=(FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        authenticationCallback=new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                //textView.setText("Error");
                //imageView.setImageResource(R.drawable.img2);
                Toast.makeText(detailedprofile.this,"Error",Toast.LENGTH_SHORT).show();
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                //textView.setText("Help");
                //imageView.setImageResource(R.drawable.img3);
                Toast.makeText(detailedprofile.this,"Help",Toast.LENGTH_SHORT).show();
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                //textView.setText("Success");
                //imageView.setImageResource(R.drawable.img1);

                Toast.makeText(detailedprofile.this,"Success",Toast.LENGTH_SHORT).show();
                super.onAuthenticationSucceeded(result);
                DatabaseReference postRef = database.getReference().child("Approvedcandidate").child(teamevent).child(teampost).child(teamkey).child("vote");

                postRef.runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {

                        Long value = mutableData.getValue(Long.class);
                        if (value == null) {
                            mutableData.setValue(1);
                        }
                        else {
                            mutableData.setValue(value + 1);
                        }

                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b,
                                           DataSnapshot dataSnapshot) {
                        // Transaction completed
                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                    }
                });
            }

            @Override
            public void onAuthenticationFailed() {
                //textView.setText("Failed");
                //imageView.setImageResource(R.drawable.img2);
                Toast.makeText(detailedprofile.this,"Failed",Toast.LENGTH_SHORT).show();
                super.onAuthenticationFailed();
            }
        };


    }

    public void scanButton(View v)
    {
        fingerprintManager.authenticate(null,null,0,authenticationCallback,null);
    }

}
