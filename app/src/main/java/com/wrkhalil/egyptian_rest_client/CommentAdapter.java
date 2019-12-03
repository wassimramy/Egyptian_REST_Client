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

    Context context;
    List<Comment> commentsList;


    public OnPostAdapterItemClickListener onPostAdapterItemClickListener;

    public CommentAdapter(Context context, List<Comment> commentsList, OnPostAdapterItemClickListener onPostAdapterItemClickListener){
        this.commentsList = commentsList;
        this.context = context;
        this.onPostAdapterItemClickListener = onPostAdapterItemClickListener;
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, final int position) {

            holder.itemTitleTextView.setText(commentsList.get(position).getName());
            holder.itemAuthorNameTextView.setText( "By: " + commentsList.get(position).getEmail());
            holder.itemBodyTextView.setText(commentsList.get(position).getBody());

        holder.itemView.setOnClickListener(v -> onPostAdapterItemClickListener.onItemClicked(position));
    }

    public int getItemCount(){
        return commentsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitleTextView, itemBodyTextView, itemAuthorNameTextView;

        public MyViewHolder (View itemView){
            super(itemView);
            itemTitleTextView = itemView.findViewById(R.id.itemTitleTextView);
            itemAuthorNameTextView = itemView.findViewById(R.id.itemAuthorNameTextView);
            itemBodyTextView = itemView.findViewById(R.id.itemBodyTextView);
        }
    }

}
