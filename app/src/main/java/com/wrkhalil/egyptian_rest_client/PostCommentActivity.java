package com.wrkhalil.egyptian_rest_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostCommentActivity extends Activity {

    TextView commentEmailTextView, commentTitleTextView, commentBodyTextView;
    Button saveButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        commentEmailTextView = findViewById(R.id.commentEmailTextView);
        commentTitleTextView = findViewById(R.id.commentTitleTextView);
        commentBodyTextView = findViewById(R.id.commentBodyTextView);

    }
}
