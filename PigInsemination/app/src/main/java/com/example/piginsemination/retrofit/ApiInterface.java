package com.example.piginsemination.retrofit;

import com.example.piginsemination.model.Category;

import com.example.piginsemination.model.Leaf;
import com.example.piginsemination.model.LevelTwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("courses.json")
    Call<List<Category>> getAllCategory();

    @GET("leveltwo.json")
    Call<List<LevelTwo>> getAllArticles();

    @GET("leaf.json")
    Call<List<Leaf>> getAllLeaf();




}
