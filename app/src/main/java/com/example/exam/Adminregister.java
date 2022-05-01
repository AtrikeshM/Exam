package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adminregister extends AppCompatActivity {

    EditText tname;
    EditText tphone;
    EditText temail;
    EditText tpassword;
    Button tbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregister);

        tname=findViewById(R.id.editTextTextPersonName1);
        temail=findViewById(R.id.editTextTextEmailAddress1);
        tphone=findViewById(R.id.editTextPhone1);
        tpassword=findViewById(R.id.editTextTextPassword1);
        tbtn=findViewById(R.id.button8);

        mAuth = FirebaseAuth.getInstance();
        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertTeacherData();

            }
        });
    }

    private void InsertTeacherData(){

        String name=tname.getText().toString();
        String email=temail.getText().toString();
        String phone=tphone.getText().toString();
        String password=tpassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tasksRef = rootRef.child("Teachers").push();
        Teachers teachers=new Teachers(name,email,phone,password);
        tasksRef.setValue(teachers);

        Toast.makeText(Adminregister.this,"Register Successfully", Toast.LENGTH_SHORT).show();

    }
}