package com.example.consumptionapicomments.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiBuilder {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private Retrofit retrofit;

    public RestApiBuilder(){
        retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JsonPlaceHolderService getService(){
        return retrofit.create(JsonPlaceHolderService.class);
    }
}
