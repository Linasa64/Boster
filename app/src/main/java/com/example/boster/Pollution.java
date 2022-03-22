package com.example.boster;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pollution extends AppCompatActivity {

    private TextView textViewPollution;
    private TextView textViewNomVille;
    private ImageView niveauPollutionImg;

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
        niveauPollutionImg = findViewById(R.id.niveauPollutionImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickMenu(){
        Intent intent = new Intent(Pollution.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisie(){
        Intent intent = new Intent(Pollution.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistorique(){
        Intent intent = new Intent(Pollution.this, Historique2.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisieB(View v){
        Intent intent = new Intent(Pollution.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistoriqueB(View v){
        Intent intent = new Intent(Pollution.this, Historique2.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Accueil:
                onClickMenu();
                break;
            case R.id.Saisir:
                onClickSaisie();
                break;
            case R.id.Historique:
                onClickHistorique();
                break;
            case R.id.Quitter:
                System.exit(0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
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
                textViewPollution.setText("Aqi : " + content);
                textViewNomVille.setText(nomVille);
                content ="";
                nomVille="";

                if(pollution<50){
                    niveauPollutionImg.setImageResource(R.drawable.level1);
                }
                else if(pollution<100){
                    niveauPollutionImg.setImageResource(R.drawable.level2);
                }
                else if(pollution<150){
                    niveauPollutionImg.setImageResource(R.drawable.level3);
                }
                else if(pollution<200){
                    niveauPollutionImg.setImageResource(R.drawable.level4);
                }
                else{
                    niveauPollutionImg.setImageResource(R.drawable.level5);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                //textViewResult.setText((t.getMessage()));
            }
        });
    }

}

