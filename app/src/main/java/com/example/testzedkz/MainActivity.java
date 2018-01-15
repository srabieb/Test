package com.example.testzedkz;

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
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://www.mocky.io/v2/5a488f243000004c15c3c57e";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<DataList> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        loadData();
    }

    private void loadData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Загружается...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                JSONObject empl = o.getJSONObject("employment");
                                DataList item = new DataList(
                                        o.getString("first_name"),
                                        o.getString("email"),
                                        o.getString("photo"),
                                        o.getString("last_name"),
                                        o.getString("gender"),
                                        o.getString("ip_address"),
                                        empl.getString("name"),
                                        empl.getString("position")

                                );
                                System.out.println(item);
                                dataList.add(item);
                            }

                            adapter = new CustomAdapter(dataList, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (final JSONException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
