package com.example.firebase02;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.ViewHolder> {

    public myRecyclerViewAdapter(ArrayList<DataModel> list,Context context) {
        this.context = context;
        this.list = list;
    }

    Context context;

    ArrayList<DataModel> list;
   // searchStudent_FB opt=new searchStudent_FB();
    MainActivity obj=new MainActivity();

    String option;


    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new ViewHolder(view);
    }
    public void setOption(String opt){
        option=opt;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel dm=list.get(position);

        holder.Name.setText(dm.getName());
        holder.CNIC.setText(String.valueOf(dm.getCNIC()));
        holder.AGE.setText(String.valueOf(dm.getAGE()));
        holder.Semester.setText(String.valueOf(dm.getSEMESTER()));
        holder.CGPA.setText(String.valueOf( dm.getCGPA()));
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(option.equals("Update")){
                    Toast.makeText(context, "Update Student", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,updateStudent.class);
                    intent.putExtra("Key",dm.getKey());
                    //Toast.makeText(context, " "+dm.getKey(), Toast.LENGTH_SHORT).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if(option.equals("Delete")){
                    Toast.makeText(context, "Delete Student", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,deleteStudent_02.class);
                    intent.putExtra("Key",dm.getKey());
                    //Toast.makeText(context, " "+dm.getKey(), Toast.LENGTH_SHORT).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        TextView Name,CNIC,AGE,Semester,CGPA;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.txt_Name);
            CNIC=itemView.findViewById(R.id.textCNIC);
            AGE=itemView.findViewById(R.id.textAGE);
            Semester=itemView.findViewById(R.id.textSemester);
            CGPA=itemView.findViewById(R.id.textCGPA);
            v=itemView;
        }
    }
}
