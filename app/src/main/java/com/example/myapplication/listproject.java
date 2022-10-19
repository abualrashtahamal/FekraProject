package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class listproject extends AppCompatActivity {
    ListView listView;
    ArrayList<String> Arraynames = new ArrayList<String>();
    ArrayList<String> Arraydetails = new ArrayList<String>();
    ArrayList<String> Arraylanguages = new ArrayList<String>();
    ArrayList<String> Arrayemails = new ArrayList<String>();
    String [] Arrayname,Arraydetail,Arraylanguage,Arrayemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listproject);
        listView=(ListView) findViewById(R.id.listdocpro);
        getprojects();

    }
    class adapter extends ArrayAdapter<String> {
        Context context;
        String[] array1, array2,array3,array4;
        //decliration variables
        public adapter(Context c, String[] v1, String[] v2, String[] v3,String[]v4) {
            super(c, R.layout.itemproject, R.id.projectname, v1);

            this.context = c;
            this.array1 = v1;
            this.array2 = v2;
            this.array3=v3;
            this.array4=v4;

        }
        @Override

        public View getView(int position, View v, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.itemproject, parent, false);

            TextView tx1 = row.findViewById(R.id.projectname);
            TextView tx2 = row.findViewById(R.id.describproblem);
            TextView tx3=row.findViewById(R.id.langproj);
            TextView tx4=row.findViewById(R.id.emdoctor);
            tx1.setText(array1[position]);
            tx2.setText(array2[position]);
            tx3.setText(array3[position]);
            tx4.setText(array4[position]);
            return row;
        }
    }
    private void getprojects() {


        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, "http://10.0.2.2/alluniversity/listproject.php", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    JSONArray jsonArray = response.getJSONArray("dishs");

                                    //Toast.makeText(getApplicationContext(), " json Array length = " +jsonArray.length(), Toast.LENGTH_SHORT).show();
                                    Arrayname = new String[jsonArray.length()];
                                    Arraydetail = new String[jsonArray.length()];
                                    Arraylanguage=new String[jsonArray.length()];
                                    Arrayemail=new String[jsonArray.length()];
                                    for(int i = 0 ; i <= jsonArray.length() ; i++){


                                        String nameproj = jsonArray.getJSONObject(i).get("projectname").toString();
                                        String detai = jsonArray.getJSONObject(i).get("projectdesc").toString();
                                        String language=jsonArray.getJSONObject(i).get("language").toString();
                                        String emdoc=jsonArray.getJSONObject(i).get("doctoremail").toString();


                                        Arrayname [i] = nameproj;
                                        Arraydetail [i] = detai;
                                        Arraylanguage[i]=language;
                                        Arrayemail[i]=emdoc;
                                        // do when the data all reseved
                                        if( i == jsonArray.length() -1){
                                            // Toast.makeText(getApplicationContext(), "Array List Names = " +ArrayNames.length, Toast.LENGTH_SHORT).show();
                                            // pass the data to the adapter
                                            adapter adp1 = new adapter(getApplicationContext(),Arrayname,Arraydetail,Arraylanguage,Arrayemail);
                                            listView.setAdapter(adp1);

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