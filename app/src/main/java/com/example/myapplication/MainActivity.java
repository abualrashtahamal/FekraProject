package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Email, password;
    SessionManager sessionManager;

    public static String utype;
    public static String uID;
    public static String username ;
    public static String u_Email ;
    public static String u_major ;
    public static String u_passw ;

    Button login;
    ImageView info;
    private String emailEt, passEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager=new SessionManager(this);


    Email = (EditText) findViewById(R.id.Email);
    password = (EditText) findViewById(R.id.password);
    //login button
    login = (Button) findViewById(R.id.login);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            if (Email.getText().toString().trim().isEmpty()) {

                Email.setError("your email Field should not be empty!");
                Email.requestFocus();
            } else {

                emailEt = Email.getText().toString();
            }
            if (password.getText().toString().trim().isEmpty()) {

                password.setError("password Field should not be empty!");
                password.requestFocus();
            } else {
                passEt = password.getText().toString();
            }
       signin();


        }
    });

        //go to description page
        info=(ImageView) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,others_page.class);
               startActivity(i);
            }
        });

    }
    //vertify email and password
    private void signin() {
        Map<String, String> params = new HashMap<String, String>();


        RequestQueue rq = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        "http://10.0.2.2/alluniversity/loginuser.php?Email=" + emailEt + "&&" + "password=" + passEt,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("dishs");

                            String stat = jsonArray.getJSONObject(0).get("Stat").toString();


                            //Toast.makeText(getApplicationContext(), "Welcome User :) stat =  " + stat, Toast.LENGTH_SHORT).show();

                            if (stat.matches("1")) {
                                uID = jsonArray.getJSONObject(0).get("Id").toString();
                                u_Email = jsonArray.getJSONObject(0).get("Email").toString();
                                username = jsonArray.getJSONObject(0).get("username").toString();
                                u_passw = jsonArray.getJSONObject(0).get("password").toString();
                                u_major = jsonArray.getJSONObject(0).get("major").toString();
                                utype = jsonArray.getJSONObject(0).get("type").toString();
                                sessionManager.createsession(u_Email,u_passw);
                                Toast.makeText(getApplicationContext(), "WELCOME "+username, Toast.LENGTH_SHORT).show();

                                if (utype.equals("doctor")) {

                                    Intent intent1 = new Intent(MainActivity.this, activ1.class);
                                    intent1.putExtra("Id", jsonArray.getJSONObject(0).get("Id").toString());
                                    intent1.putExtra("type", jsonArray.getJSONObject(0).get("type").toString());
                                    intent1.putExtra("Email", jsonArray.getJSONObject(0).get("Email").toString());
                                    startActivity(intent1);

                                } else if (utype.equals("others")) {
                                    Intent intent2 = new Intent(MainActivity.this, activ2.class);
                                    intent2.putExtra("Id", jsonArray.getJSONObject(0).get("Id").toString());
                                   intent2.putExtra("type", jsonArray.getJSONObject(0).get("type").toString());
                                      startActivity(intent2);
                                } else if (utype.equals("IT student")) {

                                    Intent intent3 = new Intent(MainActivity.this, activ3.class);
                                    intent3.putExtra("Id", jsonArray.getJSONObject(0).get("Id").toString());
                                    intent3.putExtra("type", jsonArray.getJSONObject(0).get("type").toString());

                                    startActivity(intent3);
                                }

                            } else {

                                Toast.makeText(getApplicationContext(), "No User yet!!!  ", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error Response 102" + error, Toast.LENGTH_SHORT).show();

                    }
                }
                ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Email", Email.getText().toString());
                params.put("password", password.getText().toString());


                return params;
            }


        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }

}