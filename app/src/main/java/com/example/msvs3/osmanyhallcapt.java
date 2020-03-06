package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class osmanyhallcapt extends AppCompatActivity {
    Button candidatepro,givevotebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osmanyhallcapt);
        candidatepro=findViewById(R.id.candidatepro);
        givevotebtn=findViewById(R.id.givevotebtn);
        givevotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(osmanyhallcapt.this, givevote.class);
                startActivity(intent);
            }
        });
        candidatepro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(osmanyhallcapt.this, candidateprofile.class);
                startActivity(intent);
            }
        });
    }
}
