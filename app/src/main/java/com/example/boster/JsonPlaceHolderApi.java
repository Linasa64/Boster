package com.example.boster;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("feed/@3827/")
    Call<List<Data>> getData(@Query("token") String token);
}
