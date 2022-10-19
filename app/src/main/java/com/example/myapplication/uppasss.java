package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class uppasss extends AppCompatActivity {
    public static String getEId;
    EditText newpass;
    Button updatepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uppasss);

        Intent i = getIntent();
        getEId =  i.getStringExtra("Id");

        newpass=(EditText) findViewById(R.id.newPass);
        updatepass=(Button) findViewById(R.id.updpass);
        updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newpass.getText().toString().isEmpty()) {

                    newpass.setError("please write here!");
                    newpass.requestFocus();
                }
                else{
                    Updatepass();
                    uppasss.this.finish();
                    Toast.makeText(getApplicationContext(), "password updated  ", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    private void Updatepass() {
        Map<String, String> params = new HashMap<String, String>();


        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/alluniversity/updatepassword.php",
                        response -> new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    JSONArray jsonArray = response.getJSONArray("dishs");
                                    String stat = jsonArray.getJSONObject(0).get("Stat").toString();
                                    getEId = jsonArray.getJSONObject(0).get("Id").toString();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error Response 102", Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Id", getEId.toString());
                params.put("password", newpass.getText().toString());



                return params;
            }


        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }
}