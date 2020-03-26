package com.example.msvs3;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class votevents extends Fragment {
    View view;
    Button osmanycaptbtn, processbtn, sportcaptbtn, crbtn;
    String votename,postname;
    Spinner votespin,postspin;
    TextView date,start,end;
    String ad,bs,ce;

    private DatabaseReference df1;
    public votevents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_votevents, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        processbtn=view.findViewById(R.id.processbtn);
        votespin=view.findViewById(R.id.eventspin);
        postspin=view.findViewById(R.id.postspin);
        osmanycaptbtn=view.findViewById(R.id.osmanycaptbtn);
        date=view.findViewById(R.id.datetxtb);
        start=view.findViewById(R.id.starttxtb);
        end=view.findViewById(R.id.endtxtb);

        processbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                votename = votespin.getSelectedItem().toString();
                postname = postspin.getSelectedItem().toString();
                df1 = FirebaseDatabase.getInstance().getReference().child("Events").child(votename).child(postname);
                df1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        ad = "" + ds.child("date").getValue(String.class);
                        bs = "" + ds.child("start").getValue(String.class);
                        ce = "" + ds.child("end").getValue(String.class);

                    }

                        date.setText(ad);
                        start.setText(bs);
                        end.setText(ce);
                        Intent intent=new Intent(getActivity(),osmanyhallcapt.class);
                        intent.putExtra("date",ad);
                        intent.putExtra("start",bs);
                        intent.putExtra("end",ce);
                        intent.putExtra("event",votename);
                        intent.putExtra("post",postname);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
            });

            }
        }




