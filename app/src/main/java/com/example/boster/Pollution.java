package com.example.boster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pollution extends AppCompatActivity {

    private TextView textViewPollution;
    private TextView textViewNomVille;
    private TextView textViewCommentaire;

    String idStation;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    float pollution;
    String nomVille;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.waqi.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        textViewPollution = findViewById(R.id.results);
        textViewNomVille = findViewById(R.id.nomVilleSelectionnee);
        textViewCommentaire = findViewById(R.id.commentairePollution);
    }

    public void onClickValider(View v){

        Intent intent = new Intent(Pollution.this, ChoixVilleApi.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2 && resultCode == RESULT_OK){
            if(data != null){
                idStation = ""+ data.getStringExtra("idStation");
                nomVille = "" + data.getStringExtra("nomStation");
                Toast.makeText(this, "caca : "+data.getStringExtra("nomStation"), Toast.LENGTH_SHORT).show();
                getData();
            }
        }
    }

    private void getData(){

        Call<APIResponse> call = jsonPlaceHolderApi.data(idStation);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(!response.isSuccessful()){
                    textViewPollution.setText(("Error response :" + response.code()));
                    return;
                }

                APIResponse data = response.body();
                String content = "";
                content += data.data().aqi();
                pollution = data.data().aqi();
                textViewPollution.setText("Niveau de pollution :  " + content + "  aqi");
                textViewNomVille.setText(nomVille);
                content ="";
                nomVille="";

                if(pollution<50){
                    textViewCommentaire.setText("La qualité de l'air est bonne");
                }
                else if(pollution<100){
                    textViewCommentaire.setText("La qualité de l'air est modérément bonne");
                }
                else if(pollution<150){
                    textViewCommentaire.setText("La qualité de l'air est mauvaise pour les personnes sensibles");
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                //textViewResult.setText((t.getMessage()));
            }
        });
    }

}

