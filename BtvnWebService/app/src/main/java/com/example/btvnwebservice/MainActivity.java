package com.example.btvnwebservice;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostAdapter.PostListener {
    RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> postList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(postList, this, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAdd).setOnClickListener(v -> showAddDialog());

        loadPosts();
    }

    private void showAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        var view = inflater.inflate(R.layout.dialog_post, null);
        EditText titleInput = view.findViewById(R.id.editTitle);
        EditText bodyInput = view.findViewById(R.id.editBody);

        new AlertDialog.Builder(this)
                .setTitle("Add New Post")
                .setView(view)
                .setPositiveButton("Save", (d, which) -> {
                    Post newPost = new Post();
                    newPost.userId = 1;
                    newPost.title = titleInput.getText().toString();
                    newPost.body = bodyInput.getText().toString();
                    ApiServices.getPostsApiEndpoint().createNewPost(newPost).enqueue(new Callback<Post>() {
                        public void onResponse(Call<Post> call, Response<Post> response) { loadPosts(); }
                        public void onFailure(Call<Post> call, Throwable t) {}
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void onUpdate(Post post) {
        LayoutInflater inflater = LayoutInflater.from(this);
        var view = inflater.inflate(R.layout.dialog_post, null);
        EditText titleInput = view.findViewById(R.id.editTitle);
        EditText bodyInput = view.findViewById(R.id.editBody);
        titleInput.setText(post.title);
        bodyInput.setText(post.body);

        new AlertDialog.Builder(this)
                .setTitle("Update Post")
                .setView(view)
                .setPositiveButton("Update", (d, which) -> {
                    post.title = titleInput.getText().toString();
                    post.body = bodyInput.getText().toString();
                    ApiServices.getPostsApiEndpoint().updatePost(post.id, post).enqueue(new Callback<Post>() {
                        public void onResponse(Call<Post> call, Response<Post> response) { loadPosts(); }
                        public void onFailure(Call<Post> call, Throwable t) {}
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void loadPosts() {
        ApiServices.getPostsApiEndpoint().getAllPost().enqueue(new Callback<List<Post>>() {
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postList = response.body();
                adapter.setPosts(postList);
            }
            public void onFailure(Call<List<Post>> call, Throwable t) {}
        });
    }

    public void onDelete(Post post) {
        ApiServices.getPostsApiEndpoint().deletePost(post.id).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                loadPosts();
            }
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }
}
