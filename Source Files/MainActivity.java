package com.example.firebase02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void AddStudent(View view) {
        Intent intent=new Intent(this,addStudent_FB.class);
        startActivity(intent);
    }

    public void searchStudent(View view) {
        Intent intent=new Intent(this,searchStudent_FB.class);
        intent.putExtra("option","Search");
        startActivity(intent);
    }

    public void updateStudent(View view) {
        Intent intent=new Intent(this,searchStudent_FB.class);
        intent.putExtra("option","Update");
        startActivity(intent);
    }

    public void deleteStudent(View view) {
        Intent intent=new Intent(this,searchStudent_FB.class);
        intent.putExtra("option","Delete");
        startActivity(intent);
    }
}