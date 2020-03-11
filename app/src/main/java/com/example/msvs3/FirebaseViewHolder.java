package com.example.msvs3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
    public TextView teamone,teamtwo,teamthree;
    public ImageView candidateimg;
    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        teamone=itemView.findViewById(R.id.teamone);
        teamtwo=itemView.findViewById(R.id.teamtwo);
        teamthree=itemView.findViewById(R.id.teamthree);
        //candidateimg=itemView.findViewById(R.id.candidateimg);

    }
}
