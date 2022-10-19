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
import android.widget.GridView;
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

public class documentation extends AppCompatActivity {
    GridView viewchapters;
    ArrayList<String> chnames = new ArrayList<String>();
    ArrayList<String> chnumpers = new ArrayList<String>();
    String [] chname,chnumber;
    TextView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentation);

        go=(TextView) findViewById(R.id.projects);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gointent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/1QCtSaVgfXdiKXhszlTCCABsaRpufsjZZ?usp=sharing"));
                startActivity(gointent);
            }
        });
        viewchapters=(GridView) findViewById(R.id.grid);
        getData();

    }
    class adapter extends ArrayAdapter<String> {
        Context context;
        String[] array1, array2;
        //decliration variables
        public adapter(Context c, String[] v1, String[] v2) {
            super(c, R.layout.itemch, R.id.chapternumber, v1);

            this.context = c;
            this.array1 = v1;
            this.array2 = v2;


        }
        @Override

        public View getView(int position, View v, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.itemch, parent, false);

            TextView tx1 = row.findViewById(R.id.chapternumber);
            TextView tx2 = row.findViewById(R.id.chname);


            tx1.setText(array1[position]);
            tx2.setText(array2[position]);

            return row;
        }
    }

    private void getData() {


        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/alluniversity/documentation.php", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    //Toast.makeText(getApplicationContext(), " json Array length = " +jsonArray.length(), Toast.LENGTH_SHORT).show();
                                    chname = new String[jsonArray.length()];
                                    chnumber = new String[jsonArray.length()];


                                    for(int i = 0 ; i <= jsonArray.length() ; i++){


                                        String chna = jsonArray.getJSONObject(i).get("chname").toString();
                                        String chnum = jsonArray.getJSONObject(i).get("chnumper").toString();


                                        chname [i] = chna;
                                        chnumber [i] = chnum;


                                        // do when the data all reseved
                                        if( i == jsonArray.length() -1){
                                            // Toast.makeText(getApplicationContext(), "Array List Names = " +ArrayNames.length, Toast.LENGTH_SHORT).show();
                                            // pass the data to the adapter
                                           adapter adp1 = new adapter(getApplicationContext(),chnumber,chname);
                                            viewchapters.setAdapter(adp1);
                                            viewchapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                    String i3=chname[position].toString();
                                                    String i4=chnumber[position].toString();
                                                    Intent intent=new Intent(getApplicationContext(),chapters.class);
                                                    intent.putExtra("chn",i3);
                                                    intent.putExtra("chnum",i4);
                                                    startActivity(intent);
                                                }
                                            });

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


                return params;
            }

        };
// Access the RequestQueue through your singleton class.
        rq.getCache().clear();
        rq.add(jsonObjectRequest);

    }
}