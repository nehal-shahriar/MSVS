package com.example.msvs3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.squareup.picasso.Picasso;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {
    Button userbtn,adminbtn,signbtn;
    Button regbtn;
    private ZXingScannerView scannerView;
    DatabaseReference dref;
    private static String barcodeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dref= FirebaseDatabase.getInstance().getReference("Barcode");

        userbtn=findViewById(R.id.userbtn);
        signbtn=findViewById(R.id.signinbtn);
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

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,registration.class);
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
        public void handleResult(final Result result) {
            //dref= new Firebase(Config.https://console.firebase.google.com/u/1/project/diabeticdiary-ebc55/database/diabeticdiary-ebc55/data)
            final String resultCode = result.getText();
            barcodeID=resultCode;
            System.out.println(barcodeID);
            DatabaseReference usersRef ;
            usersRef = FirebaseDatabase.getInstance().getReference().child("Info");

            //usersRef.push().child("value").setValue(resultCode);



            usersRef.orderByChild("barcodeid").equalTo(resultCode).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("Invalid User");
                        builder.setMessage("Do you want to registration?");


                        builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog

                                dialog.dismiss();
                                Intent intent=new Intent(MainActivity.this,registration.class);
                                startActivity(intent);

                            }
                        });

                        builder.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();


                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                    else
                    {
                        /*DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Info");

                        Query query=databaseReference.orderByChild("barcodeid").equalTo(barcodeID);
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds: dataSnapshot.getChildren()){
                                    String a=""+ds.child("name").getValue(String.class);
                                    String b=""+ds.child("id").getValue(String.class);
                                    String c=""+ds.child("dept").getValue(String.class);
                                    student st=(student)getApplicationContext();
                                    st.setName(a);
                                    st.setId(b);
                                    st.setDept(c);
                                    System.out.println(a);
                                    System.out.println(b);
                                    System.out.println(c);

                                    //Picasso.get().load(String.valueOf(ds.child("imgurl").getValue())).into(proimgview);
                                try {
                            }catch (Exception e){
                                Picasso.get().load(R.drawable.mist).into(proimgview);
                            }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });*/
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