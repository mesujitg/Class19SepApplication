package com.example.class19sepapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class19sepapplication.api.EmpInter;
import com.example.class19sepapplication.api.Strict;
import com.example.class19sepapplication.model.Flag;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlagApiActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    EditText editText;
    Button buttonChoose,buttonAddFlag,buttonAddC;
    Retrofit retrofit;
    EmpInter empInter;
    Uri uri;
    MultipartBody.Part image;
    String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_api);

        textView = findViewById(R.id.tvCountry);
        imageView = findViewById(R.id.ivFlag);
        buttonChoose = findViewById(R.id.btnChooseImg);
//        buttonAddFlag = findViewById(R.id.btnUploadImg);
        buttonAddC = findViewById(R.id.btnAddCon);
        editText = findViewById(R.id.etCountry);

        getInstance();
        //getCountryById(15);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.
                                Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

//        buttonAddFlag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadImage(image);
//            }
//        });

        buttonAddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = editText.getText().toString();
                uploadImageAsync(image);
                Flag flag = new Flag(0,c,file_name);
                addCountry(flag);


//                uploadImage(image);
//                addCountry(c,file_name);

//                Map<String,String> map = new HashMap<>();
//                map.put("country",c);
//                map.put("file",file_name);
//                addCountry(map);
            }
        });
    }

    private void getInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://sujitg.com.np/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        empInter = retrofit.create(EmpInter.class);
    }

    private void getCountryById(int id){
        Call<Flag> flagCall = empInter.getFlagById(id);

        flagCall.enqueue(new Callback<Flag>() {
            @Override
            public void onResponse(Call<Flag> call, Response<Flag> response) {
                textView.setText(response.body().getCountry());
                Picasso.with(FlagApiActivity.this)
                        .load("http://sujitg.com.np/wc/teams/"+
                                response.body().getFile())
                        .into(imageView);
             }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            uri = data.getData();
            imageView.setImageURI(uri);
            askPermission();
        }
    }

    public void askPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            getImgReady();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getImgReady();
            }
            else {
                Toast.makeText(this, "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getImgReady(){
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn,
                null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imgPath = cursor.getString(columnIndex);
        File file = new File(imgPath);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("image/*"),file);
        image = MultipartBody.Part.createFormData("file",
                        file.getName(),requestBody);
    }

    private void uploadImage(MultipartBody.Part image){
        Call<Flag> flagUpload = empInter.uploadFlag(image);

        flagUpload.enqueue(new Callback<Flag>() {
                @Override
                public void onResponse(Call<Flag> call, Response<Flag> response) {
                    Toast.makeText(FlagApiActivity.this,
                            response.body().getFile() +"Uploaded",
                            Toast.LENGTH_SHORT).show();
                    file_name = response.body().getFile();
                }

            @Override
            public void onFailure(Call<Flag> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }

    private void uploadImageAsync(MultipartBody.Part image){
        Call<Flag> flagUpload = empInter.uploadFlag(image);
        Strict.StrictMode();
        try {
            Response<Flag> flagResponse = flagUpload.execute();
            if(flagResponse.isSuccessful()){
                file_name = flagResponse.body().getFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCountry(String country,String file){
        Call<Void> addCon = empInter.addCountry(country,file);

        addCon.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FlagApiActivity.this,
                        "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }

    private void addCountry(Map<String,String> map){
        Call<Void> addCon = empInter.addCountryi(map);

        addCon.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FlagApiActivity.this,
                        "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }

    private void addCountry(Flag flag){
        Call<Void> addCon = empInter.addCountryii(flag);

        addCon.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FlagApiActivity.this,
                        "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Ex: ",t.getMessage());
            }
        });
    }
}
