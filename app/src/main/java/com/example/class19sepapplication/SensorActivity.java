package com.example.class19sepapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textView = findViewById(R.id.tvSensor);

        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAcc();

//        List<Sensor> sensorList =
//                sensorManager.getSensorList(Sensor.TYPE_ALL);
//        int i = 1;
//        for (Sensor s:sensorList){
//            textView.append(i +". "+ s.getName() + "\n");
//            i++;
//        }
    }

    public void sensorGyro(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SensorEventListener listener = new SensorEventListener() {
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
        SensorEventListener listener = new SensorEventListener() {
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
        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("X: "+ event.values[0]);
                if(event.values[0] > 5){
                    //any method
                    Toast.makeText(SensorActivity.this,
                            "Light is far", Toast.LENGTH_SHORT).show();
                }
                else {
                    //any method
                    Toast.makeText(SensorActivity.this,
                            "Light is close", Toast.LENGTH_SHORT).show();
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
        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("X: "+ event.values[0]);
                if(event.values[0] < 7){
                    //anymethod
                    Toast.makeText(SensorActivity.this,
                            "Light is too low", Toast.LENGTH_SHORT).show();
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


    public void sensorPedo(){
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                textView.setText("X: "+ event.values[0]);
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