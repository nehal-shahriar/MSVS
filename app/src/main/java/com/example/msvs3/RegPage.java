package com.example.msvs3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Field;
import java.util.HashMap;

public class RegPage extends AppCompatActivity {
    ImageView img;
    Button up,submit;
    EditText nameedit1, idedit, emailedit,cgedit,phnedit,barcodeedit;
    TextView barcodetxt;
    private static final int image_pick_code = 1000;
    private static final int permission_code = 1001;
    private StorageReference mStorageRef;
    private DatabaseReference dref;
    public Uri imagedata2;
    Spinner deptspinner,lvlspinner;
    String barcoderesult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);
        barcodeedit=findViewById(R.id.barcodeedit);
        nameedit1=findViewById(R.id.nameedit2);
        idedit=findViewById(R.id.idedit2);
        cgedit=findViewById(R.id.cgedit);
        emailedit=findViewById(R.id.emailedit2);
        phnedit=findViewById(R.id.phoneedit);
        deptspinner=findViewById(R.id.deptspinner);
        lvlspinner=findViewById(R.id.lvlspinner);
        img=findViewById(R.id.imagereg);
        up=findViewById(R.id.upbtn1);
        submit=findViewById(R.id.submitbtn);
        mStorageRef = FirebaseStorage.getInstance().getReference("Imagefolder");
        dref= FirebaseDatabase.getInstance().getReference().child("RequestedUser");
        try {
            Class<?> cls=Class.forName("com.example.msvs3.MainActivity");
            Field field=cls.getDeclaredField("barcodeID");
            field.setAccessible(true);
            Object object=field.get(new MainActivity());
            barcoderesult=(String)object;
            barcodeedit.setText(barcoderesult);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //==PackageManager.PERMISSION_DENIED){
                    //String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                    //requestPermissions(permissions,permission_code);
                    //}
                    //else {
                    PickImageGallery();
                } else {
                    PickImageGallery();
                }
            }

        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FireUploader();
            }
        });

    }

    private void FireUploader(){
        final StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+GetExtension(imagedata2));

        Ref.putFile(imagedata2)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String name=nameedit1.getText().toString().trim();
                                final String cg=cgedit.getText().toString().trim();
                                final String id=idedit.getText().toString().trim();
                                final String email=emailedit.getText().toString().trim();
                                final String dept = deptspinner.getSelectedItem().toString();
                                final String bar=barcodeedit.getText().toString().trim();
                                final String phone=phnedit.getText().toString().trim();
                                final String level = lvlspinner.getSelectedItem().toString();
                                final String key=dref.push().getKey();
                                HashMap<String,String>hashMap=new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("id",id);
                                hashMap.put("barcodeid",bar);
                                hashMap.put("cg",cg);
                                hashMap.put("level",level);
                                hashMap.put("dept",dept);
                                hashMap.put("email",email);
                                hashMap.put("phone",phone);
                                hashMap.put("key",key);
                                hashMap.put("imgurl",String.valueOf(String.valueOf(uri)));
                                dref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegPage.this,"uploaded",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }
    private String GetExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));

    }
    private void PickImageGallery() {
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
                    PickImageGallery();
                }
                else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == image_pick_code) {
            imagedata2 = data.getData();
            img.setImageURI(imagedata2);
        }

    }
}
