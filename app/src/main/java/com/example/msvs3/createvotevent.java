package com.example.msvs3;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class createvotevent extends Fragment {
    View view;
    Calendar c1,c2,c3;
    DatePickerDialog df1;
    TimePickerDialog tf,tf1;
    String format;
    Button calenderbtn,strttimebtn,endtimebtn,addcanbtn,voterlistbtn,createbtn;
    Spinner event, post;
    TextView datetxt,strttimetxt,endtimetxt;
    String date,start,end,events,posts;
    private DatabaseReference df;

    public createvotevent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_createvotevent, container, false);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        calenderbtn=view.findViewById(R.id.calenderbtn);
        strttimebtn=view.findViewById(R.id.starttimebtn);
        endtimebtn=view.findViewById(R.id.endtimebtn);
        createbtn=view.findViewById(R.id.createbtn);
        event=view.findViewById(R.id.event);
        post=view.findViewById(R.id.post);
        datetxt=view.findViewById(R.id.datetxt);
        strttimetxt=view.findViewById(R.id.starttimetxt);
        endtimetxt=view.findViewById(R.id.endtimetxt);
        df= FirebaseDatabase.getInstance().getReference().child("Vote_events");
        addcanbtn=view.findViewById(R.id.addcanbtn);
        addcanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),addcandidate.class);
                startActivity(intent);
            }
        });
        calenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1= Calendar.getInstance();
                int day=c1.get(Calendar.DAY_OF_MONTH);
                int month=c1.get(Calendar.MONTH);
                int year=c1.get(Calendar.YEAR);
                df1=new DatePickerDialog(getActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        //mTv.setText(mDay+"-"+(mMonth+1)+"-"+mYear);
                        //s1=String.valueOf(mDay)+"-"+String.valueOf(mMonth+1)+"-"+String.valueOf(mYear);
                        //String s=Integer.toString(mYear)+Integer.toString(mMonth+1)+Integer.toString(mDay);
                        datetxt.setText(mDay+"-"+(mMonth+1)+"-"+mYear);

                    }
                },day,month,year);
                df1.show();
            }
        });

        strttimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c2=Calendar.getInstance();
                int hour=c2.get(Calendar.HOUR_OF_DAY);
                int minute=c2.get(Calendar.MINUTE);
                selectedtimeformat(hour);

                tf=new TimePickerDialog(getActivity(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                        selectedtimeformat(mHour);
                        strttimetxt.setText(mHour+":"+mMinute+" "+format);
                    }
                },hour,minute,true);
                tf.show();
            }
        });

        endtimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c3=Calendar.getInstance();
                int hour=c3.get(Calendar.HOUR_OF_DAY);
                int minute=c3.get(Calendar.MINUTE);
                selectedtimeformat(hour);

                tf1=new TimePickerDialog(getActivity(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                        selectedtimeformat(mHour);
                        endtimetxt.setText(mHour+":"+mMinute+" "+format);
                    }
                },hour,minute,true);
                tf1.show();
            }
        });

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date=datetxt.getText().toString().trim();
                start=strttimetxt.getText().toString().trim();
                end=endtimetxt.getText().toString().trim();
                events=event.getSelectedItem().toString();
                posts=post.getSelectedItem().toString();
                String key =df.push().getKey();
                HashMap<String ,String> hash=new HashMap<>();
                hash.put("date",date);
                hash.put("start",start);
                hash.put("end",end);
                hash.put("key",key);
                df.child(events).child(posts).child(key).setValue(hash);
            }
        });

    }
    public void selectedtimeformat(int hour){
        if(hour==0){
            hour+=12;
            format="AM";}
        else if(hour==12){
            format="PM";
        }
        else if(hour>12){
            hour-=12;
            format="PM";
        }
        else{
            format="AM";
        }

    }
}
