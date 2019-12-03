package com.wrkhalil.egyptian_rest_client;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class PostsFetcher {
    private int index = 1;
    private int postsMaxBound = 0;
    private TextView postsDownloadStatusTextView;
    private MainActivity mainActivity;
    // Set JSON Request Connection Timeout (15 seconds)
    final int JSON_TIME_OUT = 15000;

    PostsFetcher(Cache cache, TextView postsDownloadStatusTextView, MainActivity mainActivity){
        this.postsDownloadStatusTextView = postsDownloadStatusTextView;
        this.mainActivity = mainActivity;
        fetchData(cache);
    }

    private void fetchData(Cache cache){
        RequestQueue requestQueue;

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        String url= "https://jsonplaceholder.typicode.com/posts/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, response -> {
                    Log.d("Response" , "Response:\n" + response);
                    String value = "null";
                    try {
                        value = response.getString("id");
                        PostsFetcher.this.postsMaxBound = Integer.parseInt(value);
                        PostsFetcher.this.postsDownloadStatusTextView.setText("Downloading " + value + " posts");
                        Log.d("Response" , "# of posts: " + value);
                        PostsFetcher.this.fetchPost(cache);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error
                    Log.d("Response" , "Failed");
                    PostsFetcher.this.fetchData(cache);
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

    private void fetchPost(Cache cache){
        RequestQueue requestQueue;

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        if (index < postsMaxBound){
            String url= "https://jsonplaceholder.typicode.com/posts/"+index;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response" , "Response:\n" + response);
                            String title, body = "null";
                            int id, userId = 0;
                            try {
                                userId = response.getInt("userId");
                                id = response.getInt("id");
                                title = response.getString("title");
                                body = response.getString("body");
                                BaseApplication.postsList.add(new Post (userId, id, title, body));
                                PostsFetcher.this.postsDownloadStatusTextView.setText("Downloading post # " + index + " UserID " + userId);
                                Log.d("Response" , "post ID: " + index);
                                index ++;
                                PostsFetcher.this.fetchPost(cache);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            PostsFetcher.this.fetchPost(cache);
                            Log.d("Response" , "Failed");
                        }
                    });

            // Add the request to the RequestQueue.
            requestQueue.add(jsonObjectRequest);
        }
        else{
            PostsFetcher.this.postsDownloadStatusTextView.setText(BaseApplication.postsList.size() + " posts were downloaded successfully");
            Log.d("postsList.size()", BaseApplication.postsList.size() + "");
            mainActivity.setPostsFetched(true);
            mainActivity.showPostsActivity();
        }
    }

}
