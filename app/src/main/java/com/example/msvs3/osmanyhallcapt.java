package com.example.msvs3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class osmanyhallcapt extends AppCompatActivity {
    Button candidatepro,givevotebtn,confirmbtn;
    TextView datea,starta,enda,maineventtxt,mainposttxt;
    String x,y,z,mainevent,mainpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osmanyhallcapt);
        candidatepro=findViewById(R.id.candidatepro);
        givevotebtn=findViewById(R.id.givevotebtn);
        datea=findViewById(R.id.datetexta);
        starta=findViewById(R.id.starttexta);
        enda=findViewById(R.id.finishtexta);
        maineventtxt=findViewById(R.id.maineventtext);
        mainposttxt=findViewById(R.id.mainposttext);
        confirmbtn=findViewById(R.id.confirmbtn);

        x=getIntent().getStringExtra("date");
        y=getIntent().getStringExtra("start");
        z=getIntent().getStringExtra("end");
        mainevent=getIntent().getStringExtra("event");
        mainpost=getIntent().getStringExtra("post");
        datea.setText(x);
        starta.setText(y);
        enda.setText(z);
        maineventtxt.setText(mainevent);
        mainposttxt.setText(mainpost);
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
                intent.putExtra("event",mainevent);
                intent.putExtra("post",mainpost);
                startActivity(intent);
            }
        });
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(osmanyhallcapt.this)
                        .setTitle("Notification Title")
                        .setMessage("Do you really want to delete the file?")
                        .setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(osmanyhallcapt.this, givevote.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.NO, null)
                        .create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    private static final int AUTO_DISMISS_MILLIS = 6000;
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                        final CharSequence negativeButtonText = defaultButton.getText();
                        new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                defaultButton.setText(String.format(
                                        Locale.getDefault(), "%s (%d)",
                                        negativeButtonText,
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                                ));
                            }
                            @Override
                            public void onFinish() {
                                if (((AlertDialog) dialog).isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        }.start();
                    }
                });
                dialog.show();
            }
        });
    }
}
