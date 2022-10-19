package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class android extends AppCompatActivity {
    ImageView youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        youtube=(ImageView) findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gointent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=OVHgh_w77Ec"));
                startActivity(gointent);
            }
        });
    }
}