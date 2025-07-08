package com.fu.khangpq.se1814maps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServices {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private PostApiEndpoint myRetrofitAPI;
    private static ApiServices instance;

    private static ApiServices getInstance() {
        if (instance == null) {
            instance = new ApiServices();
        }
        return instance;
    }

    private ApiServices() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        myRetrofitAPI = retrofit.create(PostApiEndpoint.class);
    }

    public static PostApiEndpoint getPostsApiEndpoint() {
        return getInstance().myRetrofitAPI;
    }
}
