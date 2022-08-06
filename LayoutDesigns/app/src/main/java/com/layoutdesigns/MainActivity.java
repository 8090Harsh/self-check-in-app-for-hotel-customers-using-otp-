package com.layoutdesigns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button nxt_btn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nxt_btn_id = findViewById(R.id.nxt_btn_id);

        nxt_btn_id.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nxt_btn_id:
                startActivity(new Intent(MainActivity.this,VerificationActivity_1.class));
                break;
        }
    }
}
