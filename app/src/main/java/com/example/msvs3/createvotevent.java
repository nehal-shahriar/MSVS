package com.example.msvs3;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class createvotevent extends Fragment {
    View view;
    Calendar c1,c2;
    DatePickerDialog df1;
    TimePickerDialog tf;
    String format;
    Button calenderbtn,timebtn,addcanbtn,voterlistbtn,createbtn;
    EditText dateedit,timeedit;

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
        timebtn=view.findViewById(R.id.starttimebtn);
        dateedit=view.findViewById(R.id.dateedit);
        timeedit=view.findViewById(R.id.timeedit);
        //addcanbtn=view.findViewById(R.id.addcanbtn);
        calenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1= Calendar.getInstance();
                int day=c1.get(Calendar.DAY_OF_MONTH);
                int month=c1.get(Calendar.MONTH);
                int year=c1.get(Calendar.YEAR);
                df1=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        //mTv.setText(mDay+"-"+(mMonth+1)+"-"+mYear);
                        //s1=String.valueOf(mDay)+"-"+String.valueOf(mMonth+1)+"-"+String.valueOf(mYear);
                        //String s=Integer.toString(mYear)+Integer.toString(mMonth+1)+Integer.toString(mDay);
                        dateedit.setText(mYear+"-"+(mMonth+1)+"-"+mDay);

                    }
                },day,month,year);
                df1.show();
            }
        });

        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c2=Calendar.getInstance();
                int hour=c2.get(Calendar.HOUR_OF_DAY);
                int minute=c2.get(Calendar.MINUTE);
                selectedtimeformat(hour);

                tf=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                        selectedtimeformat(mHour);
                        timeedit.setText(mHour+":"+mMinute+" "+format);
                    }
                },hour,minute,true);
                tf.show();
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
