package com.example.boster;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("feed/{id}/?token=bc68f9b0077bd7d27b6b43d4346033b8c146bb42")
    Call<APIResponse> data(@Path("id") String id);
}
