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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class candidate extends Fragment {
    View view;
    Spinner spinner,eventspinner,postspinner,lvlspin;
    ImageView imageView;
    Button imgbtn,submitbtn;
    EditText nameedit,cgedit,proedit,idedit;
    private StorageReference folder;
    private static final int image_pick_code=1000;
    private static final int permission_code=1001;


    String name, cg,id, propaganda,text,userkey,event,post,stid,lvl;

    private DatabaseReference df;
    private DatabaseReference df1;

    public candidate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_candidate, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        spinner = (Spinner) view.findViewById(R.id.spinner);
        eventspinner = (Spinner) view.findViewById(R.id.eventspinner);
        postspinner = (Spinner) view.findViewById(R.id.postspinner);
        lvlspin=view.findViewById(R.id.lvlspin);
        imageView=view.findViewById(R.id.imgview);
        submitbtn=view.findViewById(R.id.submitbtn);
        imgbtn=view.findViewById((R.id.imgbtn));
        nameedit=view.findViewById(R.id.nameedit);
        cgedit=view.findViewById(R.id.cgedit);
        idedit=view.findViewById(R.id.idedit);
        proedit=view.findViewById(R.id.proedit);
        df1=FirebaseDatabase.getInstance().getReference().child("student");
        folder= FirebaseStorage.getInstance().getReference().child("Imagefolder");
        try {
            Class<?> cls=Class.forName("com.example.msvs3.Profile_of_user");
            Field field=cls.getDeclaredField("studentid");
            field.setAccessible(true);
            Object object=field.get(new Profile_of_user());
            stid=(String)object;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        df = FirebaseDatabase.getInstance().getReference().child("Approveduser");
        Query query = df.orderByChild("id").equalTo(stid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String a = "" + ds.child("name").getValue(String.class);
                    String b = "" + ds.child("cg").getValue(String.class);
                    String c = "" + ds.child("id").getValue(String.class);
                    String d="" + ds.child("imgurl").getValue(String.class);
                    nameedit.setText(a);
                    cgedit.setText(b);
                    idedit.setText(c);
                    Picasso.get().load(d).into(imageView);

                    //Picasso.get().load(String.valueOf(ds.child("imgurl").getValue())).into(proimgview);
                    //try {
                    //} catch (Exception e) {
                    //  Picasso.get().load(R.drawable.mist).into(proimgview);
                    //}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




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
                                public void onSuccess(final Uri uri) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                    builder.setTitle("Confirm");
                                    builder.setMessage("Are you sure?");

                                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do nothing but close the dialog
                                            name=nameedit.getText().toString();
                                            cg=cgedit.getText().toString();
                                            id=idedit.getText().toString();
                                            propaganda=proedit.getText().toString();
                                            text = spinner.getSelectedItem().toString();
                                            event=eventspinner.getSelectedItem().toString();
                                            post=postspinner.getSelectedItem().toString();
                                            userkey=df.push().getKey();
                                            lvl=lvlspin.getSelectedItem().toString();
                                            HashMap<String,String> hashMap=new HashMap<>();
                                            hashMap.put("imgurl",String.valueOf(uri));
                                            hashMap.put("name",name);
                                            hashMap.put("id",id);
                                            hashMap.put("cg",cg);
                                            hashMap.put("dept",text);
                                            hashMap.put("propaganda",propaganda);
                                            hashMap.put("key",userkey);
                                            hashMap.put("event",event);
                                            hashMap.put("post",post);
                                            hashMap.put("level",lvl);

                                            df1.child(userkey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(),"Your request is sent to Admin for approval",Toast.LENGTH_SHORT).show();
                                                }
                                            });

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

                                    //DatabaseReference imgstore= FirebaseDatabase.getInstance().getReference().child("test");

                                }
                            });
                        }
                    });
                }
            });


        }
    }
}
