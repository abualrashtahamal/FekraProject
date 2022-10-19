package com.example.myapplication;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class update_delete extends AppCompatActivity {
    EditText et1, et2;
    String s1, s2, idpost;
    ImageView b, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.des);
        //get data by intent
        Intent i = getIntent();
        s1 = i.getStringExtra("na");
        s2 = i.getStringExtra("de");
        idpost = i.getStringExtra("idpost");
        et1.setText(s1);
        et2.setText(s2);
        //Image update
        b = (ImageView) findViewById(R.id.update);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                update_delete.this.finish();
                Toast.makeText(getApplicationContext(), "data updated  " , Toast.LENGTH_SHORT).show();
            }
        });
//Image delete
        d = (ImageView) findViewById(R.id.delete);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
                update_delete.this.finish();
                Toast.makeText(getApplicationContext(),"deleted ok ",Toast.LENGTH_SHORT).show();
            }
        });

    }
//update data
    private void updateData() {
        Map<String, String> params = new HashMap<String, String>();


        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/alluniversity/updateposts.php",
                        response -> new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("dishs");
                                    String stat = jsonArray.getJSONObject(0).get("Stat").toString();
                                    idpost = jsonArray.getJSONObject(0).get("Id").toString();

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
                params.put("Id", idpost.toString());
                params.put("nameprob", et1.getText().toString());
                params.put("describprob", et2.getText().toString());

                return params;
            }
  };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }


//delete
private void delete() {
    Map<String,String> params =new HashMap<String,String>();
    RequestQueue rq = Volley.newRequestQueue(this);

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.POST, "http://10.0.2.2/alluniversity/delete.php?Id="+idpost.toString(),
                    null,new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray = response.getJSONArray("dishs");

                        String stat =    jsonArray.getJSONObject(0).get("status").toString();
                        idpost = jsonArray.getJSONObject(0).get("Id").toString();

 //Toast.makeText(getApplicationContext(),"Welcome User :) stat =  "+stat,Toast.LENGTH_SHORT).show();

                        if(stat.matches("ok")){



                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    Toast.makeText(getApplicationContext(),"Error Response 102"+error,Toast.LENGTH_LONG).show();
                }
            }
            ){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String>params=new HashMap<String, String>();

            params.put("Id",idpost.toString());


            return  params;
        }
//


    };

// Access the RequestQueue through your singleton class.
    rq.add(jsonObjectRequest);
}
}
