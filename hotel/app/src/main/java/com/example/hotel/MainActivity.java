package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etUserPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = Name.getText().toString();
                String uPass = Password.getText().toString();
                if(uName.isEmpty() || uPass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"please enter all the details",Toast.LENGTH_LONG).show();
                }else {
                    validate(Name.getText().toString(), Password.getText().toString());
                }
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
    }
    private void validate(String userName,String userPassword)
    {   progressDialog.setMessage("Please wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {
                   progressDialog.dismiss();
                  // Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                   checkEmailVerification();
               }else{
                   Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                   progressDialog.dismiss();
               }
            }
        });
    }


    private void checkEmailVerification()
    {
    FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
    Boolean emailflag =firebaseUser.isEmailVerified();
    if(emailflag){
        finish();
        startActivity(new Intent(MainActivity.this,MainActivity2.class));
    }else{
        Toast.makeText(this,"verify your email",Toast.LENGTH_SHORT).show();
        firebaseAuth.signOut();
    }
    }
}