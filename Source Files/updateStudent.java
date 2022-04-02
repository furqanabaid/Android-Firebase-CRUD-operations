package com.example.firebase02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class updateStudent extends AppCompatActivity {
    TextInputLayout Name, CNIC, AGE, SEMESTER, CGPA;
    String Key;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Name =findViewById(R.id.name_update);
        CNIC =findViewById(R.id.cnic_update);
        AGE =findViewById(R.id.age_update);
        SEMESTER =findViewById(R.id.semester_update);
        CGPA =findViewById(R.id.cgpa_update);
        Key=getIntent().getStringExtra("Key");
        Toast.makeText(updateStudent.this, "key: "+Key, Toast.LENGTH_SHORT).show();
        reference= FirebaseDatabase.getInstance().getReference().child("Student");
        reference.child(Key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    DataModel dataModel=snapshot.getValue(DataModel.class);
                    Toast.makeText(updateStudent.this, "value: "+dataModel, Toast.LENGTH_SHORT).show();
                    Name.getEditText().setText(dataModel.getName());
                    CNIC.getEditText().setText(String.valueOf(dataModel.getCNIC()));
                    AGE.getEditText().setText(String.valueOf(dataModel.getAGE()));
                    SEMESTER.getEditText().setText(String.valueOf(dataModel.getSEMESTER()));
                    CGPA.getEditText().setText(String.valueOf(dataModel.getCGPA()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void updateStudent(View view) {
        Map<String,Object> map=new HashMap<>();
        map.put("name", Name.getEditText().getText().toString());
        map.put("cnic", CNIC.getEditText().getText().toString());
        map.put("age", AGE.getEditText().getText().toString());
        map.put("semester", SEMESTER.getEditText().getText().toString());
        map.put("cgpa", CGPA.getEditText().getText().toString());

        reference.child(Key).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(updateStudent.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goBackToHome(View view) {
        Intent intent=new Intent(updateStudent.this,MainActivity.class);
        startActivity(intent);
    }
}