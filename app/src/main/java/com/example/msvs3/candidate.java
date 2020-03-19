package com.example.msvs3;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class candidate extends Fragment {
    View view;
    Spinner spinner;
    ImageView imageView;
    Button imgbtn,submitbtn;
    EditText nameedit,cgedit,proedit;
    private StorageReference folder;
    private static final int image_pick_code=1000;
    private static final int permission_code=1001;

    String name, cg, propaganda,text,userkey;

    private DatabaseReference df;

    public candidate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_candidate, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        spinner = (Spinner) view.findViewById(R.id.spinner);
        imageView=view.findViewById(R.id.imgview);
        submitbtn=view.findViewById(R.id.submitbtn);
        imgbtn=view.findViewById((R.id.imgbtn));
        nameedit=view.findViewById(R.id.nameedit);
        cgedit=view.findViewById(R.id.cgedit);
        proedit=view.findViewById(R.id.proedit);
        df=FirebaseDatabase.getInstance().getReference().child("student");
        folder= FirebaseStorage.getInstance().getReference().child("Imagefolder");



        /*submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/
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
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
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
            final Uri imagedata=data.getData();
            imageView.setImageURI(imagedata);
            submitbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final StorageReference imagename=folder.child("image"+imagedata.getLastPathSegment());
                    imagename.putFile(imagedata).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //DatabaseReference imgstore= FirebaseDatabase.getInstance().getReference().child("test");
                                    name=nameedit.getText().toString();
                                    cg=cgedit.getText().toString();
                                    propaganda=proedit.getText().toString();
                                    text = spinner.getSelectedItem().toString();
                                    userkey=df.push().getKey();
                                    HashMap<String,String> hashMap=new HashMap<>();
                                    hashMap.put("imgurl",String.valueOf(uri));
                                    hashMap.put("name",name);
                                    hashMap.put("cg",cg);
                                    hashMap.put("department",text);
                                    hashMap.put("propaganda",propaganda);
                                    hashMap.put("key",userkey);

                                    df.push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getActivity(),"Your request is sent to Admin for approval",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });


        }
    }
}
