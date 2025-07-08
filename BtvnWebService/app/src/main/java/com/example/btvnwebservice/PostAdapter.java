package com.example.btvnwebservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> posts;
    private Context context;
    public interface PostListener {
        void onUpdate(Post post);
        void onDelete(Post post);
    }
    private PostListener listener;

    public PostAdapter(List<Post> posts, Context context, PostListener listener) {
        this.posts = posts;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;
        Button btnUpdate, btnDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.title);
        holder.body.setText(post.body);
        holder.btnUpdate.setOnClickListener(v -> listener.onUpdate(post));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(post));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}