package com.example.class19sepapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.class19sepapplication.R;
import com.example.class19sepapplication.database.DbHelper;
import com.example.class19sepapplication.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    List<Student> studentList = new ArrayList<>();
    Context context;
    EditText tvId,tvN,tvE,tvP;

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    public void setTvId(EditText tvId) {
        this.tvId = tvId;
    }

    public void setTvN(EditText tvN) {
        this.tvN = tvN;
    }

    public void setTvE(EditText tvE) {
        this.tvE = tvE;
    }

    public void setTvP(EditText tvP) {
        this.tvP = tvP;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_student_rv,parent,false);
        StudentHolder studentHolder = new StudentHolder(view);
        return studentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        final Student student = studentList.get(position);
        holder.tvStName.setText(student.getName());
        holder.tvStEmail.setText(student.getEmail());
        holder.tvStPhone.setText(student.getPhone());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvN.setText(student.getName());
                tvE.setText(student.getEmail());
                tvP.setText(student.getPhone());
                tvId.setText(String.valueOf(student.getId()));
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(context);
                if(dbHelper.deleteStudent(student.getId())){
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder{

        TextView tvStName,tvStEmail,tvStPhone;
        ImageView imageView;
        Button btnUpdate,btnDelete;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvStName = itemView.findViewById(R.id.tvStName);
            tvStEmail = itemView.findViewById(R.id.tvStEmail);
            tvStPhone = itemView.findViewById(R.id.tvStPhone);
            imageView = itemView.findViewById(R.id.ivStImg);
            btnUpdate = itemView.findViewById(R.id.btnStUpdate);
            btnDelete = itemView.findViewById(R.id.btnStDelete);
        }
    }
}
