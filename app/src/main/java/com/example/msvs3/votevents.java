package com.example.msvs3;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class votevents extends Fragment {
    View view;
    Button osmanycaptbtn, mistcaptbtn, sportcaptbtn, crbtn;



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
        osmanycaptbtn=view.findViewById(R.id.osmanycaptbtn);
        osmanycaptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getActivity(),MainActivity.class);
                //startActivity(intent);
                Toast.makeText(getActivity(),"testing",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),osmanyhallcapt.class);
                startActivity(intent);
            }
        });

    }

}
