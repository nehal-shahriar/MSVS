package com.example.msvs3;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class candidate extends Fragment {
    View view;
    Spinner spinner;
    ImageView imageView;
    Button imgbtn;

    private static final int image_pick_code=1000;
    private static final int permission_code=1001;

    public candidate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_candidate, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        spinner = (Spinner) view.findViewById(R.id.spinner);
        imageView=view.findViewById(R.id.imgview);
        imgbtn=view.findViewById((R.id.imgbtn));
        //spinner.setOnItemSelectedListener(this.getActivity());

        // Spinner Drop down elements
        //List<String> categories = new ArrayList<String>();
        //categories.add("Random");
        //categories.add("Fasting");
        //categories.add("After meal");
        String [] values =
                {"CIVIL","CSE","EECE","AE","IPE","ME","NSE","NAME"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    //if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        //==PackageManager.PERMISSION_DENIED){
                        //String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //requestPermissions(permissions,permission_code);
                    //}
                    //else {
                        PickImageFromGallery();
                    }

                else {
                    PickImageFromGallery();
                }
            }
        });
    }

    private void PickImageFromGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,image_pick_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case permission_code:{
                if(grantResults.length>0 && grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
                    PickImageFromGallery();
                }
                else{
                    Toast.makeText(this.getActivity(),"permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==image_pick_code){
            imageView.setImageURI(data.getData());

        }
    }
}
