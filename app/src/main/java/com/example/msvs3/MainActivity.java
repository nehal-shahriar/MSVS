package com.example.msvs3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {
    Button userbtn,adminbtn;
    Button regbtn;
    private ZXingScannerView scannerView;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref= FirebaseDatabase.getInstance().getReference("Barcode");

        userbtn=findViewById(R.id.userbtn);
        regbtn=findViewById(R.id.regbtn);
        adminbtn=findViewById((R.id.adminbtn));
        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,user.class);
                startActivity(intent);
            }
        });
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,admin.class);
                startActivity(intent);
            }
        });


    }
    public void scanCode(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();

    }
    @Override
    public void onPause(){
        super.onPause();
        scannerView.stopCamera();

    }




    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {

        @Override
        public void handleResult(Result result) {
            //dref= new Firebase(Config.https://console.firebase.google.com/u/1/project/diabeticdiary-ebc55/database/diabeticdiary-ebc55/data)
            final String resultCode = result.getText();
            DatabaseReference usersRef ;
            usersRef = FirebaseDatabase.getInstance().getReference().child("Info");

            //usersRef.push().child("value").setValue(resultCode);



            usersRef.orderByChild("value").equalTo(resultCode).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override

                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int flag=0;
                    for(DataSnapshot data: dataSnapshot.getChildren()) {
                        if (!dataSnapshot.exists()) {

                            Toast.makeText(MainActivity.this, "Not valid", Toast.LENGTH_SHORT).show();
                        } else {


                            flag=1;
                        }
                    }
                    if(flag==0)
                    {
                        Toast.makeText(MainActivity.this, "Not valid", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "valid barcode", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this,user.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            setContentView(R.layout.activity_main);
            scannerView.stopCamera();
        }
    }

}