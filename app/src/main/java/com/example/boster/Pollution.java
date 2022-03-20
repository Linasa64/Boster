package com.example.boster;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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

        Call<List<Data>> call = jsonPlaceHolderApi.getData("bc68f9b0077bd7d27b6b43d4346033b8c146bb42");

        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText(("Code :" + response.code()));
                    return;
                }

                List<Data> datas = response.body();
                for(Data data : datas){
                    String content = "";
                    //content += "AQI : " + data.getAqi();
                    //content += "IDX : " + data.getIdx();
                    content += "Status : " + data.status();
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }

}

