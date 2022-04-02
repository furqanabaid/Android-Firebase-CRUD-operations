package com.example.firebase02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class deleteStudent_02 extends AppCompatActivity {
    TextView name_text,age_text,cnic_text,semester_text,cgpa_text;
    String Key;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student02);
        name_text=findViewById(R.id.name_Text);
        age_text=findViewById(R.id.age_Text);
        cnic_text=findViewById(R.id.cnic_text);
        semester_text=findViewById(R.id.semester_text);
        cgpa_text=findViewById(R.id.cgpa_text);
        Key=getIntent().getStringExtra("Key");
        reference= FirebaseDatabase.getInstance().getReference().child("Student");
        reference.child(Key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    DataModel dataModel=snapshot.getValue(DataModel.class);
                    Toast.makeText(deleteStudent_02.this, "value: "+dataModel, Toast.LENGTH_SHORT).show();
                    name_text.setText(dataModel.getName());
                    cnic_text.setText(String.valueOf(dataModel.getCNIC()));
                    age_text.setText(String.valueOf(dataModel.getAGE()));
                    semester_text.setText(String.valueOf(dataModel.getSEMESTER()));
                    cgpa_text.setText(String.valueOf(dataModel.getCGPA()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteStudent_btn(View view) {
        reference.child(Key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(deleteStudent_02.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}