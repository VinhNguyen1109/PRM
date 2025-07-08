package com.example.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApiEndpoint {
    @GET("posts")
    Call<List<Post>> getAllPost();

    @GET("posts/{postId}")
    Call<Post> getPostById(@Path("postId") int id);

    @GET("posts")
    Call<List<Post>> getPostByUserId(@Query("userId") int uid);

    @POST("posts")
    Call<Post> createNewPost(@Body Post p);

    @DELETE("posts/{postId}")
    Call<Void> deletePost(@Path("postId") int id);

    @PUT("posts/{postId}")
    Call<Post> replacePost(@Path("postId") int id, @Body Post p);
}