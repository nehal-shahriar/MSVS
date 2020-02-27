package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {
    Button userbtn,adminbtn;
    private ZXingScannerView scannerView;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref= FirebaseDatabase.getInstance().getReference("Result1");

        userbtn=findViewById(R.id.userbtn);
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

            }
        });

    }
    public void scancode(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
    }
    @Override
    public void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }
    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{
        @Override
        public void handleResult(com.google.zxing.Result result){
            String resultCode= result.getText();
            String key=dref.push().getKey();
            dref.child(key).setValue(resultCode);

            setContentView(R.layout.activity_main);
            scannerView.stopCamera();
        }
    }

}
