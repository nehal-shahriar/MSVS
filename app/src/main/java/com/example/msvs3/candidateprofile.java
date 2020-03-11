package com.example.msvs3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class candidateprofile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DataSetFire> arrayList;
    private FirebaseRecyclerOptions<DataSetFire> options;
    private FirebaseRecyclerAdapter<DataSetFire,FirebaseViewHolder> adapter;
    private DatabaseReference df;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidateprofile);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<DataSetFire>();
        df= FirebaseDatabase.getInstance().getReference().child("Info");
        df.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(df,DataSetFire.class).build();

        adapter=new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull final DataSetFire model) {
                firebaseViewHolder.teamone.setText(model.getName());
                firebaseViewHolder.teamtwo.setText(model.getId());
                firebaseViewHolder.teamthree.setText(model.getDept());
                firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(candidateprofile.this,detailedprofile.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("id",model.getId());
                        intent.putExtra("dept",model.getDept());
                        startActivity(intent);
                    }
                });
            }



            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                return new FirebaseViewHolder(LayoutInflater.from(candidateprofile.this).inflate(R.layout.row,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);

    }
}
