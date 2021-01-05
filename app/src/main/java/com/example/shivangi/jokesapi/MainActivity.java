package com.example.shivangi.jokesapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JokesAdapter jokesAdapter;
    private ArrayList<Jokes> jokesArrayList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jokesArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        getJokesData();
    }

    private void getJokesData() {
        String url = "https://official-joke-api.appspot.com/random_ten";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i ++){
                                JSONObject jokes = (JSONObject)response.get(i);

                                String question = jokes.getString("setup");
                                String answer = jokes.getString("punchline");
                                jokesArrayList.add(new Jokes(question,answer));
                            }

                            jokesAdapter = new JokesAdapter(MainActivity.this,jokesArrayList);
                            recyclerView.setAdapter(jokesAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    },new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
        requestQueue.add(request);
    }
}