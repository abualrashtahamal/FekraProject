package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class company extends AppCompatActivity {

Button t1,t2,t3,t4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        t1=(Button) findViewById(R.id.one);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Intent.ACTION_DIAL);
                i1.setData(Uri.parse("tel:123456789"));
                startActivity(i1);
            }
        });
        t2=(Button) findViewById(R.id.tow);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Intent.ACTION_DIAL);
                i1.setData(Uri.parse("tel:258741639"));
                startActivity(i1);
            }
        });
        t3=(Button) findViewById(R.id.three);
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Intent.ACTION_DIAL);
                i1.setData(Uri.parse("tel:198563247"));
                startActivity(i1);
            }
        });
        t4=(Button) findViewById(R.id.four);
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Intent.ACTION_DIAL);
                i1.setData(Uri.parse("tel:5987463201"));
                startActivity(i1);
            }
        });
    }
}