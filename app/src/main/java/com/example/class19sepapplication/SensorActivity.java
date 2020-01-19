package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener listener;
    TextView textView,textView1;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textView = findViewById(R.id.tvSensor);
        textView1 = findViewById(R.id.tvStatus);
        spinner = findViewById(R.id.spSensor);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sensorManager.unregisterListener(listener,sensor);
                String value = parent.getItemAtPosition(position).toString();
                if(value.equals("All")){
                    textView1.setText("");
                    getAllSensor();
                }
                if(value.equals("Gyroscope")){
                    textView1.setText("");
                    sensorGyro();
                }
                if(value.equals("Accelerometer")){
                    sensorAcc();
                }
                if(value.equals("Proximity")){
                    textView1.setText("");
                    sensorProxi();
                }
                if(value.equals("Light")){
                    textView1.setText("");
                    sensorLight();
                }
                if(value.equals("Pedometer")){
                    textView1.setText("");
                    sensorPedo();
                }
                if(value.equals("Temperature")){
                    textView1.setText("");
                    sensorTemp();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getAllSensor(){
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        int i = 1;
        for (Sensor s:sensorList){
            textView.append(i +". "+ s.getName() + "\n");
            i++;
        }
    }

    public void sensorGyro(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("X: "+ event.values[0] +"\n"+
                        "Y: "+ event.values[1] +"\n"+
                        "Z: "+ event.values[2] +"\n");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void sensorAcc(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("X: "+ event.values[0] +"\n"+
                        "Y: "+ event.values[1] +"\n"+
                        "Z: "+ event.values[2] +"\n");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void sensorProxi(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("Distance: "+ event.values[0]);
                if(event.values[0] >= 5){
                    //any method
                    textView1.setText("Object is far");
                }
                else {
                    //any method
                    textView1.setText("Object is close");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void sensorLight(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("Intensity: "+ event.values[0]);
                if(event.values[0] < 7){
                    //anymethod
                    textView1.setText("Light is too low");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            textView1.setText("Requested sensor is not available");
        }
    }


    public void sensorPedo(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("Steps: "+ event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void sensorTemp(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("Temperature: "+ event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null){
            sensorManager
                    .registerListener(listener,sensor,
                            SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this,
                    "Requested sensor is not available",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
