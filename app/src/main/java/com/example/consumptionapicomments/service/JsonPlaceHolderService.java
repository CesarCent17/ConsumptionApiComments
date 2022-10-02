package com.example.consumptionapicomments.service;

import com.example.consumptionapicomments.model.Comments;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderService {
    @GET("comments")
    Call<List<Comments>> getComments();
}
