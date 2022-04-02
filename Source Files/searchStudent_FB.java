package com.example.firebase02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class searchStudent_FB extends AppCompatActivity {
    DatabaseReference myRef;
    TextInputLayout SearchTextInput;
    String search1;
    private RecyclerView myRecyclerView;
    private myRecyclerViewAdapter adapter;
    ArrayList<DataModel> list;
    double[] x = new double[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_fb);
        myRecyclerView=findViewById(R.id.recView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list=new ArrayList<>();
        adapter=new myRecyclerViewAdapter(list,getApplicationContext());
        myRecyclerView.setAdapter(adapter);
        SearchTextInput=findViewById(R.id.search);
        myRef = FirebaseDatabase.getInstance("https://smd-firebase02-lab-default-rtdb.firebaseio.com/").getReference();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setOption(getIntent().getStringExtra("option"));
    }

    public void searchStudent(View view) {
        search1 =SearchTextInput.getEditText().getText().toString();
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot1, @Nullable String previousChildName) {
                for (DataSnapshot snapshot: snapshot1.getChildren()) {
                    if (snapshot.child("name").getValue(String.class).equals(search1)) {
                        DataModel dataModel = new DataModel();
                        dataModel.setName(snapshot.child("name").getValue(String.class));
                        dataModel.setSEMESTER(snapshot.child("semester").getValue(String.class));
                        dataModel.setAGE(snapshot.child("age").getValue(String.class));
                        dataModel.setCGPA(snapshot.child("cgpa").getValue(String.class));
                        dataModel.setCNIC(snapshot.child("cnic").getValue(String.class));
                        dataModel.setKey(snapshot.child("key").getValue(String.class));
                        list.add(dataModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DataModel dataModel=snapshot.getValue(DataModel.class);
                if(dataModel.getName().equals(search1) ){
                    list.add(dataModel);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "CHild REmoved: ");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("TAG", "child moved: ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG", "Cancelled: ");
            }
        });

    }

    public void goBackToHome(View view) {
        Intent intent=new Intent(searchStudent_FB.this,MainActivity.class);
        startActivity(intent);
    }
}