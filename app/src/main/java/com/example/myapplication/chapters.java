package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chapters extends AppCompatActivity
{
TextView chname,chnumber;
ListView chlist;
String s3,s4;
    ArrayList<String> Arraychtopics = new ArrayList<String>();
    ArrayList<String> Arraychdescribs= new ArrayList<String>();
    String [] Arraychtopic,Arraychdescrib;
            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        chlist=(ListView)findViewById(R.id.listch) ;
        chnumber=(TextView)findViewById(R.id.chnumber) ;
        chname=(TextView)findViewById(R.id.name1);

        Intent i=getIntent();
        s3=i.getStringExtra("chnum");
        s4=i.getStringExtra("chn");
        chnumber.setText(s3);
        chname.setText(s4);
        getData();





    }
    class adapter extends ArrayAdapter<String> {
        Context context;
        String[] array1, array2;
        //decliration variables
        public adapter(Context c, String[] v1, String[] v2) {
            super(c, R.layout.topicsdesc, R.id.topic, v1);

            this.context = c;
            this.array1 = v1;
            this.array2 = v2;


        }
        @Override

        public View getView(int position, View v, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.topicsdesc, parent, false);

            TextView tx1 = row.findViewById(R.id.topic);
            TextView tx2 = row.findViewById(R.id.desc);


            tx1.setText(array1[position]);
            tx2.setText(array2[position]);

            return row;
        }
    }
    private void getData() {


        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/alluniversity/discdoc.php?chnumper="+s3, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    s3 =    jsonArray.getJSONObject(0).get("chnumper").toString();

                                    //Toast.makeText(getApplicationContext(), " json Array length = " +jsonArray.length(), Toast.LENGTH_SHORT).show();
                                    Arraychtopic = new String[jsonArray.length()];
                                    Arraychdescrib = new String[jsonArray.length()];

                                    for(int i = 0 ; i <= jsonArray.length() ; i++){


                                        String topic = jsonArray.getJSONObject(i).get("Topics").toString();
                                        String desctop = jsonArray.getJSONObject(i).get("Descipch").toString();

                                        Arraychtopic [i] = topic;
                                        Arraychdescrib [i] = desctop;

                                        // do when the data all reseved
                                        if( i == jsonArray.length() -1){
                                            // Toast.makeText(getApplicationContext(), "Array List Names = " +ArrayNames.length, Toast.LENGTH_SHORT).show();
                                            // pass the data to the adapter
                                            adapter adp1 = new adapter(getApplicationContext(),Arraychtopic,Arraychdescrib);
                                            chlist.setAdapter(adp1);


                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "Error Response In PHP File ", Toast.LENGTH_SHORT).show();

                    }
                }

                ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();
                params.put("chnumper",s3);

                // you must have in the php file a post method to get this data
                // ex : $_POST['DataToSend'];
                return params;
            }

        };
// Access the RequestQueue through your singleton class.
        rq.getCache().clear();
        rq.add(jsonObjectRequest);

    }
}