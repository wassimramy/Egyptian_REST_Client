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

public class CommentsFetcher {
    private int index = 1;
    private int commentsMaxBound = 0;
    private TextView commentsDownloadStatusTextView;
    private MainActivity mainActivity;

    CommentsFetcher(Cache cache, TextView commentsDownloadStatusTextView, MainActivity mainActivity){
        this.commentsDownloadStatusTextView = commentsDownloadStatusTextView;
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

        String url= "https://jsonplaceholder.typicode.com/comments/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, response -> {
                    Log.d("Response" , "Response:\n" + response);
                    String value = "null";
                    try {
                        value = response.getString("id");
                        CommentsFetcher.this.commentsMaxBound = Integer.parseInt(value);
                        CommentsFetcher.this.commentsDownloadStatusTextView.setText("Downloading " + value + " posts");
                        Log.d("Response" , "# of posts: " + value);
                        CommentsFetcher.this.fetchComment(cache);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // TODO: Handle error
                    Log.d("Response" , "Failed");
                    CommentsFetcher.this.fetchData(cache);
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest);
    }

    private void fetchComment(Cache cache){
        RequestQueue requestQueue;

        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

        // Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

        // Start the queue
        requestQueue.start();

        if (index < commentsMaxBound){
            String url= "https://jsonplaceholder.typicode.com/comments/"+index;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, response -> {
                        Log.d("Response" , "Response:\n" + response);
                        String name, email, body = "null";
                        int id, postId = 0;
                        try {
                            postId = response.getInt("postId");
                            id = response.getInt("id");
                            name = response.getString("name");
                            email = response.getString("email");
                            body = response.getString("body");
                            BaseApplication.commentsList.add(new Comment (postId, id, name, email, body));
                            CommentsFetcher.this.commentsDownloadStatusTextView.setText("Downloading comment # " + index);
                            Log.d("Response" , "comment Body: " + body);
                            index ++;
                            CommentsFetcher.this.fetchComment(cache);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            CommentsFetcher.this.fetchComment(cache);
                            Log.d("Response" , "Failed");
                        }
                    });

            // Add the request to the RequestQueue.
            requestQueue.add(jsonObjectRequest);
        }
        else{
            CommentsFetcher.this.commentsDownloadStatusTextView.setText(BaseApplication.postsList.size() + " posts were downloaded successfully");
            Log.d("commentsList.size()", BaseApplication.commentsList.size() + "");
            mainActivity.setCommentsFetched(true);
            mainActivity.showPostsActivity();
        }
    }

}
