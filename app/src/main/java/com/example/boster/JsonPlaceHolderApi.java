package com.example.boster;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("feed/@3827/?token=bc68f9b0077bd7d27b6b43d4346033b8c146bb42")
    Call<APIResponse> data();
}
