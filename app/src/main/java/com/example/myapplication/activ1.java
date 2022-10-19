package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class activ1 extends AppCompatActivity {
    ImageView profile,suggest,list,addpr;


    public static String getExtra,gettypeextra,getemailextra;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activ1);
        //get data by intent
        Intent i = getIntent();
        getExtra =  i.getStringExtra("Id");
        gettypeextra=i.getStringExtra("type");
        getemailextra=i.getStringExtra("Email");
        //go to profile
        profile=(ImageView) findViewById(R.id.pro);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent pro=new Intent(activ1.this,profilestudent.class);
              pro.putExtra("Id",getExtra);
              startActivity(pro);
            }
        });
        //go to projects doctor
        addpr=(ImageView) findViewById(R.id.addpro);
        addpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imt=new Intent(getApplicationContext(),projectsdoctor.class);
                imt.putExtra("email",getemailextra);
                startActivity(imt);
            }
        });
        suggest=(ImageView)findViewById(R.id.suggestdoctor) ;
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent go=new Intent(getApplicationContext(),suggest.class);
             go.putExtra("Id",getExtra);
             go.putExtra("type",gettypeextra);
               startActivity(go);
            }
        });
        //show list problem
        list=(ImageView) findViewById(R.id.listproblemdoc);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intee=new Intent(activ1.this,showlist.class);
               intee.putExtra("typeuser",gettypeextra);
               startActivity(intee);
            }
        });
    }

}