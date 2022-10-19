package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class flutter extends AppCompatActivity {
    ImageView youtubef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        youtubef=(ImageView) findViewById(R.id.youtubef);
        youtubef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gointent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=vBU7SrGJWWQ"));
                startActivity(gointent);
            }
        });
    }
}