package com.example.firebase02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addStudent_FB extends AppCompatActivity {
    DatabaseReference myRef;
    TextInputLayout name,cnic,age,semester,cgpa;
    String AGE;
    String SEMESTER;
    String CGPA;
    String CNIC;
    String Name;
    private String id;
    private boolean check=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_fb);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://smd-firebase02-lab-default-rtdb.firebaseio.com/");
        myRef = database.getReference("Student");
        name=findViewById(R.id.name);
        cnic=findViewById(R.id.cnic);
        age=findViewById(R.id.age);
        semester=findViewById(R.id.semester);
        cgpa=findViewById(R.id.cgpa);

    }

    public void addStudent(View view) {
        Name=name.getEditText().getText().toString();
        CNIC=cnic.getEditText().getText().toString();
        AGE= age.getEditText().getText().toString();
        SEMESTER=semester.getEditText().getText().toString();
        CGPA=cgpa.getEditText().getText().toString();
        DataModel dataModel =new DataModel();
        dataModel.setName(Name);
        dataModel.setCNIC(CNIC);
        dataModel.setAGE(AGE);
        dataModel.setSEMESTER(SEMESTER);
        dataModel.setCGPA(CGPA);
        id=myRef.push().getKey();
        dataModel.setKey(id);
        Toast.makeText(this, "id"+id, Toast.LENGTH_SHORT).show();
        myRef.child(id).setValue(dataModel);
        finish();
    }

    public void goToHome(View view) {
        Intent intent=new Intent(addStudent_FB.this,MainActivity.class);
        startActivity(intent);

    }
}