package com.example.hotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity9 extends AppCompatActivity {
    private static final String TAG = "MainActivity9";
    private Button room;
    private EditText time;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        room = (Button)findViewById(R.id.btnSltRoom);
        time = (EditText)findViewById(R.id.editTextTime);
        firebaseAuth = FirebaseAuth.getInstance();

        mDisplayDate = (TextView) findViewById(R.id.tvDate);



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity9.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("date");

                String Time = time.getText().toString();
                String Date = mDisplayDate.getText().toString();


                Usr user = new Usr(Time,Date);
                reference.child(firebaseAuth.getUid()).setValue(user);

                if(Time.isEmpty() || Date.isEmpty())
                {
                    Toast.makeText(MainActivity9.this,"please enter all the details",Toast.LENGTH_LONG).show();
                }else
                {

                    startActivity(new Intent(MainActivity9.this,MainActivity10.class));
                }
            }
        });





    }

}