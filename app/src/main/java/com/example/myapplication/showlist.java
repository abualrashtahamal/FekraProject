package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class showlist extends AppCompatActivity {
    private static String type;
    TextView tx3;
    ListView listViewstudent;
    ArrayList<String> Arraynames = new ArrayList<String>();
    ArrayList<String> Arraydescriptions = new ArrayList<String>();
    ArrayList<String> Arraytypeusers = new ArrayList<String>();
    String [] Arrayname,Arraydescription,Arraytypeuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlist);
        Intent ii = getIntent();
        type=ii.getStringExtra("typeuser");
        listViewstudent= (ListView) findViewById(R.id.lists);
        //method get data
        getData();
    }
    //adapter
    class adapter extends ArrayAdapter<String> {
        Context context;
        String[] array1, array2,array3;
        //decliration variables
        public adapter(Context c, String[] v1, String[] v2, String[] v3) {
            super(c, R.layout.items, R.id.problemname, v1);

            this.context = c;
            this.array1 = v1;
            this.array2 = v2;
            this.array3=v3;

        }
        @Override

        public View getView(int position, View v, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.items, parent, false);

            TextView tx1 = row.findViewById(R.id.problemname);
            TextView tx2 = row.findViewById(R.id.describproblem);
             tx3=row.findViewById(R.id.typeuser);

            tx1.setText(array1[position]);
            tx2.setText(array2[position]);
            tx3.setText(array3[position]);
            return row;
        }
    }
    //show list data
    private void getData() {


        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/alluniversity/listsuggest.php", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    //Toast.makeText(getApplicationContext(), " json Array length = " +jsonArray.length(), Toast.LENGTH_SHORT).show();
                                    Arrayname = new String[jsonArray.length()];
                                    Arraydescription = new String[jsonArray.length()];
                                    Arraytypeuser=new String[jsonArray.length()];

                                    for(int i = 0 ; i <= jsonArray.length() ; i++){


                                        String nameprob = jsonArray.getJSONObject(i).get("nameprob").toString();
                                        String describprob = jsonArray.getJSONObject(i).get("describprob").toString();
                                        String typeuser=jsonArray.getJSONObject(i).get("typeuser").toString();


                                        Arrayname [i] = nameprob;
                                        Arraydescription [i] = describprob;
                                        Arraytypeuser[i]=typeuser;

                                        // do when the data all reseved
                                        if( i == jsonArray.length() -1){
                                            // Toast.makeText(getApplicationContext(), "Array List Names = " +ArrayNames.length, Toast.LENGTH_SHORT).show();
                                            // pass the data to the adapter
                                            adapter adp1 = new adapter(getApplicationContext(),Arrayname,Arraydescription,Arraytypeuser);
                                            listViewstudent.setAdapter(adp1);

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
              //  params.put("typeuser",type.toString());

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