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

public class addcandidate extends AppCompatActivity {

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
        setContentView(R.layout.activity_addcandidate);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<DataSetFire>();
        df= FirebaseDatabase.getInstance().getReference().child("student");
        df.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<DataSetFire>().setQuery(df,DataSetFire.class).build();

        adapter=new FirebaseRecyclerAdapter<DataSetFire, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull final DataSetFire model) {
                firebaseViewHolder.teamname1.setText(model.getName());
                firebaseViewHolder.teamcg.setText(model.getCg());
                firebaseViewHolder.teamdept.setText(model.getDepartment());
                firebaseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(addcandidate.this,approvecandidate.class);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("cg",model.getCg());
                        intent.putExtra("department",model.getDepartment());
                        intent.putExtra("propaganda",model.getPropaganda());
                        intent.putExtra("imgurl",model.getImgurl());
                        startActivity(intent);
                    }
                });
            }



            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                return new FirebaseViewHolder(LayoutInflater.from(addcandidate.this).inflate(R.layout.rowforcandidate,viewGroup,false));
            }
        };

        recyclerView.setAdapter(adapter);

    }
}
