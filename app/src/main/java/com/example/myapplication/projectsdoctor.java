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

public class projectsdoctor extends AppCompatActivity {
EditText etname,etdetails,etlanguage;
Button add;
    public static String getemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        getemail =  i.getStringExtra("email");

        setContentView(R.layout.activity_projectsdoctor);
        etname=(EditText) findViewById(R.id.nameproject);
        etdetails=(EditText) findViewById(R.id.detproject);
        etlanguage=(EditText) findViewById(R.id.lang);
        add=(Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addprojects();
                projectsdoctor.this.finish();
            }
        });
    }

    private void addprojects() {
        Map<String,String> params =new HashMap<String,String>();



        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/alluniversity/projectsdoctor.php",response ->
                        new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    String stat =    jsonArray.getJSONObject(0).get("Stat").toString();

                                    Toast.makeText(getApplicationContext(),"welcom  "+stat,Toast.LENGTH_SHORT).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(),"Error Response 102",Toast.LENGTH_SHORT).show();

                    }
                }

                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();

                params.put("projectname",etname.getText().toString());
                params.put("projectdesc",etdetails.getText().toString());
                params.put("language",etlanguage.getText().toString());
                params.put("doctoremail",getemail.toString());
                return  params;

            }

        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }
}