package com.example.msvs3;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class result extends Fragment {
    View view;
    ListView listview;
    //DatabaseReference df;
    Spinner spinner2;
    //ArrayList<String> mUsername=new ArrayList<>();
    String text1;

    private RecyclerView recyclerView;
    private ArrayList<DataSetFire> arrayList;
    private FirebaseRecyclerOptions<DataSetFire> options;
    private FirebaseRecyclerAdapter<DataSetFire,FirebaseViewHolder> adapter;
    private DatabaseReference df;
    Button refreshbtn;

    public result() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_result, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //df= FirebaseDatabase.getInstance().getReference("student");
        //listview=view.findViewById(R.id.lstview);
        spinner2=view.findViewById(R.id.spinner2);
        text1 = spinner2.getSelectedItem().toString();
        recyclerView=view.findViewById(R.id.recylceview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        refreshbtn=view.findViewById(R.id.refreshbtn);

        arrayList=new ArrayList<DataSetFire>();


        df= FirebaseDatabase.getInstance().getReference().child(text1);
        //Query query=df.orderByChild("name").equalTo(text1);
        df.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(df,DataSetFire.class).build();

        adapter=new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull final DataSetFire model) {


                firebaseViewHolder.teamname.setText(model.getName());
                firebaseViewHolder.teamid.setText(model.getId());
                firebaseViewHolder.teamvoteno.setText(model.getVote());
                /*firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(),detailedprofile.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("id",model.getId());
                        intent.putExtra("dept",model.getDept());
                        startActivity(intent);
                    }
                });*/

            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                return new FirebaseViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.rowforvote,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);
        /*final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mUsername);
        listview.setAdapter(arrayAdapter);
        text = spinner2.getSelectedItem().toString();
        df.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    String value=(String)dataSnapshot1.getValue();
                    mUsername.add(value);

                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }



    @Override
    public void onStart() {
        super.onStart();

        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
