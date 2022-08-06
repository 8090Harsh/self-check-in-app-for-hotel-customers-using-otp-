package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity8 extends AppCompatActivity {
    private EditText Uguest;
    private EditText Uroom;
    private Button select;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        Uguest = (EditText) findViewById(R.id.etGuest);
        Uroom = (EditText) findViewById(R.id.etRoom);
        select = (Button) findViewById(R.id.btnSelect);
        firebaseAuth = FirebaseAuth.getInstance();

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("guest");

                String room = Uroom.getText().toString();
                String guest = Uguest.getText().toString();


                Users user = new Users(room,guest);
                reference.child(firebaseAuth.getUid()).setValue(user);

                if(room.isEmpty() || guest.isEmpty())
                {
                    Toast.makeText(MainActivity8.this,"please enter all the details",Toast.LENGTH_LONG).show();
                }else
                {

                    startActivity(new Intent(MainActivity8.this,MainActivity9.class));
                }
            }
        });





    }
}