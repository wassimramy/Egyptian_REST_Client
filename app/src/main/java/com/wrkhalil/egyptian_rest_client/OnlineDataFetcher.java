package com.wrkhalil.egyptian_rest_client;

import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class OnlineDataFetcher {
    private String url;
    // Set JSON Request Connection Timeout (15 seconds)
    final int JSON_TIME_OUT = 15000;

    OnlineDataFetcher(String url){
        this.url = url;
        fetchData();
    }

    private void fetchData(){

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // Display the first 500 characters of the response string.
                    //response = response.substring(0, 500);
                    Log.d("Response from JSONPlaceholder\n:", "Data is fetched successfully\n" + response);

                    try {
                        Log.d("JSON Parser", "Started");
                        //JSONObject jsonObject = new JSONObject(response);
                        JSONArray postArray = response.getJSONArray("");



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> Log.d("Response from JSONPlaceholder\n:", "Data is unavailable"));

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(JSON_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add the request to the RequestQueue.
        BaseApplication.queue.add(jsonObjectRequest);

    }

    private void JSONParser (String response){


    }
}
