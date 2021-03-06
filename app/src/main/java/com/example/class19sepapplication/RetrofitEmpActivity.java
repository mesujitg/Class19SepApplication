package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class19sepapplication.api.EmpInter;
import com.example.class19sepapplication.model.Employee;
import com.example.class19sepapplication.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEmpActivity extends AppCompatActivity {

    TextView textView;
    EditText etName,etSalary,etAge;
    Button btnAdd;

    Retrofit retrofit;
    EmpInter empInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_emp);

        textView = findViewById(R.id.empApiData);
        etName = findViewById(R.id.empName);
        etSalary = findViewById(R.id.empSalary);
        etAge = findViewById(R.id.empAge);
        btnAdd = findViewById(R.id.empAdd);

        getInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String salary = etSalary.getText().toString();
                String age = etAge.getText().toString();
                Employee employee =
                        new Employee(0,name,salary,age);
                updateEmployee(180458,employee);
            }
        });
    }

    private void getInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empInter = retrofit.create(EmpInter.class);
    }

    private void getAllEmployees(){
        Call<List<Employee>> employeeList =
                empInter.getEmployees();
        employeeList.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> empList = response.body();
                for(Employee emp: empList){
                    //Log.d("name",emp.getEmployee_name());
                    textView.append("Name: "+ emp.getEmployee_name() +"    Salary: "+
                            emp.getEmployee_salary() +"    Age: "+ emp.getEmployee_age() +"\n");
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("ApiEx: ", t.getMessage());
            }
        });
    }

    private void getEmpById(){
        Call<Employee> employee = empInter.getEmployeeById(1);

        employee.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(RetrofitEmpActivity.this,
                        response.body().getEmployee_name(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(RetrofitEmpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addEmployee(Employee employee){
        Call<Void> empAdd = empInter.addEmployee(employee);

        empAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitEmpActivity.this,
                        "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }

    private void updateEmployee(int id,Employee employee){
        Call<Void> empUpdate = empInter.updateEmployee(id,employee);

        empUpdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitEmpActivity.this,
                        "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }
}
