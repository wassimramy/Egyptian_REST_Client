package com.wrkhalil.egyptian_rest_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        Log.d("Response:", "Class is instantiated\n");
        fetchData();
        //fetchDataGet();
        //BaseApplication.queue = Volley.newRequestQueue(BaseApplication.getAppContext());
        //OnlineDataFetcher postsDataFetcher = new OnlineDataFetcher("https://jsonplaceholder.typicode.com/posts/");

        //OnlineDataFetcher usersDataFetcher = new OnlineDataFetcher("https://jsonplaceholder.typicode.com/users");

        /*
                //Retrieve the position of the item clicked in the recycleView and send it to startItemEditActivity to show the respective item information
        PostAdapter postAdapter = new PostAdapter(this, BaseApplication.postsList, position -> {
            showPostActivity(position);
        });
        recyclerView.setAdapter(postAdapter); //Update the recyclerView
         */



    }

    private void fetchData(){
        RequestQueue requestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        String url= "https://jsonplaceholder.typicode.com/posts/";

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("");
                            Log.d("Array Size", jsonArray.toString() + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("Response" , "Response:\n" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    private void fetchDataJSON(){
        RequestQueue requestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        String url= "https://jsonplaceholder.typicode.com/posts/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response" , "Response:\n" + response);
                        String value = "null";
                        try {
                            value = response.getString("id");
                            Log.d("Response" , "# of posts: " + value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Response" , "Failed");

                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

    private void fetchDataGet(){
        RequestQueue requestQueue;

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        String url= "https://jsonplaceholder.typicode.com/posts/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response" , "Response:\n" + response);
                        String id, userID, title, body = "null";
                        try {
                            id = response.getString("id");
                            userID = response.getString("userID");
                            title = response.getString("title");
                            body = response.getString("body");
                            //Log.d("Response" , "post ID: " + value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("Response" , "Failed");

                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }



    private void showPostActivity(int position){
        //Intent intent = new Intent(this, PostViewerActivity.class);
        //intent.putExtra("Position", position); //Sends the URI value to the ShowPictureActivity to fetch the picture
        //startActivity(intent); //Start the activity
        this.finish();
    }
}
