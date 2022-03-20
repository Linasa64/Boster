package com.example.boster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pollution extends AppCompatActivity {

    private TextView textViewResult;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution);

        textViewResult = findViewById(R.id.results);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.waqi.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getData();
    }

    private void getData(){

        Call<APIResponse> call = jsonPlaceHolderApi.data();

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText(("Error response :" + response.code()));
                    return;
                }

                APIResponse data = response.body();
                String content = "";
                content += "AQI : " + data.data().aqi();
                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }

}

