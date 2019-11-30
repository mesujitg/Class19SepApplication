package com.example.class19sepapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ImInActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCamera,buttonWebsite,buttonGallery,buttonDialer;
    EditText editText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_in);

        buttonCamera = findViewById(R.id.btnCamera);
        buttonWebsite = findViewById(R.id.btnUrl);
        buttonGallery = findViewById(R.id.btnGallery);
        buttonDialer = findViewById(R.id.btnDialer);
        editText = findViewById(R.id.etDialNumber);
        imageView = findViewById(R.id.selectedImg);

        buttonCamera.setOnClickListener(this);
        buttonWebsite.setOnClickListener(this);
        buttonGallery.setOnClickListener(this);
        buttonDialer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
                break;

            case R.id.btnUrl:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.softwarica.edu.np"));
                startActivity(intent1);
                break;

            case R.id.btnGallery:
                Intent intent2 = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.
                                Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 2);
                break;

            case R.id.btnDialer:
                Uri uri = Uri.parse("tel:"+ editText.getText().toString());
                Intent intent3 = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
        }

        if(requestCode == 2){
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}
