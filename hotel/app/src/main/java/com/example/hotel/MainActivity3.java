package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity3 extends AppCompatActivity {
    private Button next;
    private EditText Uname;
    private EditText Uphone;
    private EditText Uaddress;
    private EditText Uage;
    private EditText Uvisits;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Uname = (EditText)findViewById(R.id.editName);
        Uphone = (EditText)findViewById(R.id.editPhone);
        Uaddress = (EditText)findViewById(R.id.editAddress);
        Uage = (EditText)findViewById(R.id.etage);
        Uvisits = (EditText)findViewById(R.id.editVisit);
        firebaseAuth = FirebaseAuth.getInstance();
        next =(Button)findViewById(R.id.btnnext);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = Uname.getText().toString();
                String phone = Uphone.getText().toString();
                String address = Uaddress.getText().toString();
                String age = Uage.getText().toString();
                String visits = Uvisits.getText().toString();

                User user = new User(name,phone,address,age,visits);
                reference.child(firebaseAuth.getUid()).setValue(user);

                if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || age.isEmpty() || visits.isEmpty())
                {
                    Toast.makeText(MainActivity3.this,"please enter all the details",Toast.LENGTH_LONG).show();
                }else
                {

                    startActivity(new Intent(MainActivity3.this,MainActivity4.class));
                }
                 }
        });


    }





}