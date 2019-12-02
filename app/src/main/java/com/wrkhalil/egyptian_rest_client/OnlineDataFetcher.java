package com.wrkhalil.egyptian_rest_client;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OnlineDataFetcher {
    private String url;

    OnlineDataFetcher(String url){
        this.url = url;
        fetchData();
    }

    private void fetchData(){

        RequestQueue queue = Volley.newRequestQueue(BaseApplication.getAppContext());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                    Log.d("Response from JSONPlaceholder\n:", "SRT File is fetched successfully\n" + response);
                }, error -> Log.d("Response from JSONPlaceholder\n:", "Data is unavailable"));
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
