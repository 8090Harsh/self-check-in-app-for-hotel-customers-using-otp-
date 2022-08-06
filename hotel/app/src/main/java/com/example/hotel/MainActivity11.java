package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity11 extends AppCompatActivity {
    private TextView roomNo;
    private TextView time;
    private TextView date;
    FirebaseAuth firebaseAuth;
    FirebaseAuth firebaseAuth2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    DatabaseReference reference2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        roomNo = (TextView)findViewById(R.id.textroom);
        date = (TextView)findViewById(R.id.textdate);
        time = (TextView)findViewById(R.id.texttime);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth2 = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference2 = firebaseDatabase.getReference("room").child(firebaseAuth2.getUid());
        reference = firebaseDatabase.getReference("date").child(firebaseAuth.getUid());

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Uss value = snapshot.getValue(Uss.class);
                if (value != null) {
                    roomNo.setText(value.getRoom());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usr val = snapshot.getValue(Usr.class);


                if (val != null) {
                    date.setText(val.getDate());
                }
                if (val != null) {
                    time.setText(val.getTime());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}