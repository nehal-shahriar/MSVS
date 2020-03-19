package com.example.msvs3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class approve extends AppCompatActivity {
    TextView usernametxt,useridtxt,userdepttxt,useremailtxt,userbarcodeidtxt;
    Button approvebtn;
    private DatabaseReference df;
    private DatabaseReference df1;
    String teama,teamb,teamc,teame,teamd,teamf;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve);
        usernametxt=findViewById(R.id.nametxtview);
        useridtxt=findViewById(R.id.idtxtview);
        userdepttxt=findViewById(R.id.depttxtview);
        useremailtxt=findViewById(R.id.emailtxtview);
        userbarcodeidtxt=findViewById(R.id.barcodeidtxtview);
        approvebtn=findViewById(R.id.approvebtn);
        teama=getIntent().getStringExtra("name");
        teamb=getIntent().getStringExtra("id");
        teamc=getIntent().getStringExtra("dept");
        teamd=getIntent().getStringExtra("email");
        teame=getIntent().getStringExtra("barcodeid");
        teamf=getIntent().getStringExtra("position");
        i=Integer.parseInt(teamf);
        //Log.i("OUR VALUE",teamone);
        //Log.i("OUR VALUE 2",teamtwo);
        //Log.i("OUR VALUE 3",teamthree);
        Toast.makeText(this,""+teamb,Toast.LENGTH_SHORT).show();
        usernametxt.setText(teama);
        useridtxt.setText(teamb);
        userdepttxt.setText(teamc);
        useremailtxt.setText(teamd);
        userbarcodeidtxt.setText(teame);
        approvebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                df= FirebaseDatabase.getInstance().getReference().child("Approveduser");
                Voter voter=new Voter();
                voter.setName(teama);
                voter.setId(teamb);
                voter.setDept(teamc);
                voter.setEmail(teamd);
                voter.setBarcodeid(teame);
                df.push().setValue(voter);
                Toast.makeText(approve.this,"voter is approved",Toast.LENGTH_SHORT).show();
                //df1=FirebaseDatabase.getInstance().getReference().child("feedback");

                String Subject="Approval from Admin";
                String feed="You are approved by Admin as a Voter. Congratulations!";
                JavaMailAPI javaMailAPI= new JavaMailAPI(approve.this,teamd,Subject,feed);
                javaMailAPI.execute();



            }
        });
    }
}
