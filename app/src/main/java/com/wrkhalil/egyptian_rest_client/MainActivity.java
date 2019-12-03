package com.wrkhalil.egyptian_rest_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    private Cache cache;
    private boolean usersFetched, commentsFetched, postsFetched = false;
    private TextView usersDownloadStatusTextView;
    private TextView commentsDownloadStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView postsDownloadStatusTextView = findViewById(R.id.postsDownloadStatusTextView);
        usersDownloadStatusTextView = findViewById(R.id.usersDownloadStatusTextView);
        commentsDownloadStatusTextView = findViewById(R.id.commentsDownloadStatusTextView );

        Log.d("Response:", "Class is instantiated\n");
        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        PostsFetcher postsFetcher = new PostsFetcher(cache, postsDownloadStatusTextView, this);
        CommentsFetcher commentsFetcher = new CommentsFetcher(cache, commentsDownloadStatusTextView, this);
        UsersFetcher usersFetcher = new UsersFetcher(cache, usersDownloadStatusTextView, this);

    }

    public void showPostsActivity(){
        if (usersFetched && commentsFetched && postsFetched){
            Intent intent = new Intent(this, PostsActivity.class);
            startActivity(intent); //Start the activity
            this.finish();
        }
    }

    public void setPostsFetched(boolean postsFetched) {
        this.postsFetched = postsFetched;
    }

    public void setCommentsFetched(boolean commentsFetched) {
        this.commentsFetched = commentsFetched;
    }

    public void setUsersFetched(boolean usersFetched) {
        this.usersFetched = usersFetched;
    }
}
