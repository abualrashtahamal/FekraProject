package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class suggest extends AppCompatActivity {
    EditText named,describd;
    Button sugg;
    private String namedEt,describdEt;
    public static String getExtra,gettypeextra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest);
        Intent i = getIntent();
        getExtra =  i.getStringExtra("Id");
        gettypeextra=i.getStringExtra("type");
        named=(EditText)findViewById(R.id.nameprobl) ;
        describd=(EditText)findViewById(R.id.descprobl) ;

        sugg=(Button)findViewById(R.id.sugg);
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (named.getText().toString().isEmpty()) {

                    named.setError("please write here!");
                    named.requestFocus();
                }
                else if (describd.getText().toString().isEmpty()) {
                    describd.setError("please write here");
                    describd.requestFocus();

                }else{
                    sendData();

                    Toast.makeText(getApplicationContext(),"suggestion added",Toast.LENGTH_LONG).show();
                }
}
        });
    }
    private void sendData() {
        Map<String,String> params =new HashMap<String,String>();
        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest
                (Request.Method.POST,
                        "http://10.0.2.2/alluniversity/descother.php",response ->
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    String stat =    jsonArray.getJSONObject(0).get("Stat").toString();


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

                params.put("nameprob",named.getText().toString());
                params.put("describprob",describd.getText().toString());
                params.put("userId",getExtra.toString());
                params.put("typeuser",gettypeextra.toString());
                return  params;

            }

        };

// Access the RequestQueue through your singleton class.
        rq.add(jsonObjectRequest);
    }

}

