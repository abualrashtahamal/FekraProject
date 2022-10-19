package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class activ3 extends AppCompatActivity
{
    TextView profile,suggest,list,documentation,programming;

    public static String getExtra,gettypeextra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activ3);
        //go to documentation activity
        documentation=(TextView)findViewById(R.id.document);
        documentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),documentation.class);
                startActivity(i);
            }
        });
        //get data by intent
        Intent i = getIntent();
        getExtra =  i.getStringExtra("Id");
        gettypeextra=i.getStringExtra("type");
        //go to profile
        profile=(TextView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pro=new Intent(activ3.this,profilestudent.class);
                pro.putExtra("Id",getExtra);
                startActivity(pro);
            }
        });
        //go to programmer
        programming=(TextView)findViewById(R.id.proggramming);
        programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),programmingtracks.class));
            }
        });




        suggest=(TextView) findViewById(R.id.suggest) ;
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getApplicationContext(),suggest.class);
                go.putExtra("Id",getExtra);
                go.putExtra("type",gettypeextra);
                startActivity(go);



            }
        });
        //shoe list problem
        list=(TextView) findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intee=new Intent(activ3.this,showlist.class);
                intee.putExtra("typeuser",gettypeextra);
                startActivity(intee);
            }
        });


    }

}