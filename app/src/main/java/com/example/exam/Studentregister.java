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

public class Studentregister extends AppCompatActivity {

    EditText stname;
    EditText stphone;
    EditText stemail;
    EditText stpassword;
    Button stbtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregister);

        stname=findViewById(R.id.editTextTextPersonName);
        stemail=findViewById(R.id.editTextTextEmailAddress);
        stphone=findViewById(R.id.editTextPhone);
        stpassword=findViewById(R.id.editTextTextPassword);
        stbtn=findViewById(R.id.button7);

        mAuth = FirebaseAuth.getInstance();
        stbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InsertStudentData();

            }
        });
    }
    private void InsertStudentData(){

        String name=stname.getText().toString();
        String email=stemail.getText().toString();
        String phone=stphone.getText().toString();
        String password=stpassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tasksRef = rootRef.child("Students").push();
        Students students=new Students(name,email,phone,password);
        tasksRef.setValue(students);

        Toast.makeText(Studentregister.this,"Register Successfully", Toast.LENGTH_SHORT).show();

    }
}