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

public class activ2 extends AppCompatActivity {
    ImageView profilestudent,suggeststudent,liststudent;

    public static String getExtraS,gettypeextraS;

AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activ2);
        //get data by intent
        Intent i = getIntent();
        getExtraS =  i.getStringExtra("Id");
        gettypeextraS=i.getStringExtra("type");
        //go to profile student
        profilestudent=(ImageView) findViewById(R.id.profilestudent);
        profilestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile=new Intent(activ2.this,profilestudent.class);
                profile.putExtra("Id",getExtraS);
                startActivity(profile);
            }
        });




        suggeststudent=(ImageView)findViewById(R.id.suggeststudent) ;
        suggeststudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go=new Intent(getApplicationContext(),suggest.class);
                go.putExtra("Id",getExtraS);
                go.putExtra("type",gettypeextraS);
                startActivity(go);
            }
        });
        //list of problem for student
        liststudent=(ImageView) findViewById(R.id.listproblemstudent);
        liststudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intee=new Intent(activ2.this,showlist.class);
                intee.putExtra("typeuser",gettypeextraS);
                startActivity(intee);
            }
        });
    }

}