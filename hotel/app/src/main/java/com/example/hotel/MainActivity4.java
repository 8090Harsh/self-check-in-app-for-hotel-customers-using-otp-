package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity4 extends AppCompatActivity {
    private ImageView Front;
    private ImageView back;
    private Button room;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth firebaseAuth;
    private static int PICK_IMAGE =123;
    private  static int PICK_IMAGES = 124;
    private StorageReference storageReference;
    Uri imagePath;
    Uri imageP;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null) {
                imagePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                    Front.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == PICK_IMAGES && resultCode == RESULT_OK && data.getData() != null) {
            imageP = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageP);
                back.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Front =(ImageView)findViewById(R.id.imageView6);
        back = (ImageView)findViewById(R.id.imageView7);
        room = (Button)findViewById(R.id.btnRoom);

        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        storageReference = firebaseStorage.getReference();

        Front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGES);
            }
        });
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("images").child("id front");
                UploadTask uploadTask = imageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity4.this,"Front Document Upload Failed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(MainActivity4.this,"Front Document Upload Successful",Toast.LENGTH_SHORT).show();
                    }
                });
                StorageReference imageReference1 = storageReference.child(firebaseAuth.getUid()).child("images").child("id back");
                UploadTask uploadTasks = imageReference1.putFile(imageP);
                uploadTasks.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity4.this,"Back Document Upload Failed",Toast.LENGTH_SHORT).show();

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(MainActivity4.this,"Back Document Upload Successful",Toast.LENGTH_SHORT).show();

                    }
                });

                if(imagePath ==null || imageP==null)
                {
                    Toast.makeText(MainActivity4.this,"Please Upload The Document",Toast.LENGTH_LONG).show();
                }else
                {
                    startActivity(new Intent(MainActivity4.this,MainActivity8.class));
                }

            }
        });




    }
}