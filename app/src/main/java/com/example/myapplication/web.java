package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class web extends AppCompatActivity {
ImageView youtubew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        youtubew=(ImageView) findViewById(R.id.youtubew);
        youtubew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gointent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=w7xZl0N9ey8"));
                startActivity(gointent);
            }
        });
    }
}