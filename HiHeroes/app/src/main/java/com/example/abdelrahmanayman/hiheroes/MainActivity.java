package com.example.abdelrahmanayman.hiheroes;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter adapter ;
    private List<ListItem> listItems ;
    private String ArrayResult = "" ;

    private static final String imageUrl = "https://gist.githubusercontent.com/Abdelrahman1993ayman/6183e9498d1f88e8e17b4a1b49eb2b78/raw/c1a963348d1a4d3750581477d6260d34ffc7317a/Heroes.JSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        LoadRecyclerViewData();
    }

    private void LoadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.... ");
        progressDialog.show();

        StringRequest stringRequest =   new StringRequest(Request.Method.GET, imageUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Heroes");
                 //   JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        ListItem item = new ListItem(object.getString("name") ,object.getString("bio")
                        ,object.getString("imageurl"));

                        listItems.add(item);
                    }
                    adapter= new MyAdapter(listItems , getApplicationContext());
                    recyclerView.setAdapter(adapter);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
