package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    private Button checkin;
    private FirebaseAuth firebaseAuth;
    private  Button logout;
    private Button details;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkin = (Button)findViewById(R.id.btncheckin);
        details = (Button)findViewById(R.id.btnDtl);


        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Logout();

            }
        });

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity11.class));
            }
        });

    }


    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity2.this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.LogoutMenu:{
                Logout();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
