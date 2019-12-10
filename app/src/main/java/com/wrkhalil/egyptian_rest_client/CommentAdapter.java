package com.wrkhalil.egyptian_rest_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    //Attributes
    Context context;
    List<Comment> commentsList;
    public OnPostAdapterItemClickListener onPostAdapterItemClickListener;

    //CommentAdapter constructor
    public CommentAdapter(Context context, List<Comment> commentsList, OnPostAdapterItemClickListener onPostAdapterItemClickListener){
        this.commentsList = commentsList; //Fetch commentsList
        this.context = context; //Fetch context
        this.onPostAdapterItemClickListener = onPostAdapterItemClickListener; //Fetch on onPostAdapterItemClickListener
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment, parent, false); //Inflate the "row_comment" layout
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, final int position) {

            holder.itemTitleTextView.setText(commentsList.get(position).getName()); //Display the comment name
            holder.itemAuthorNameTextView.setText( "By: " + commentsList.get(position).getEmail()); //Display the commentator's name
            holder.itemBodyTextView.setText(commentsList.get(position).getBody()); //Display the comment's body
            holder.itemView.setOnClickListener(v -> onPostAdapterItemClickListener.onItemClicked(position)); //Return teh position when the item is clicked
    }

    public int getItemCount(){
        return commentsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitleTextView, itemBodyTextView, itemAuthorNameTextView;

        public MyViewHolder (View itemView){
            super(itemView);

            //TextViews Declaration
            itemTitleTextView = itemView.findViewById(R.id.itemTitleTextView);
            itemAuthorNameTextView = itemView.findViewById(R.id.itemAuthorNameTextView);
            itemBodyTextView = itemView.findViewById(R.id.itemBodyTextView);
        }
    }

}
