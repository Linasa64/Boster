package com.example.boster;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pollution extends AppCompatActivity {

    private TextView textViewResult;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    //Association entre le nom de la station et son identifiant sur le site internet
    Station toulMaz = new Station("Toulouse Mazades", "@3827");
    Station toulBer = new Station("Toulouse Berthelot", "@3828");
    Station toulRAlbi = new Station("Toulouse-Rte D'Albi", "@3829");
    Station blagPiste = new Station("Blagnac Aéroport Côté Pistes", "@3830");
    Station lourdes = new Station("Lourdes Lapacca", "@3831");
    Station toulZIChap = new Station("Toulouse ZI Chapitre", "@3832");
    Station toulEizen = new Station("Toulouse Eisenhower", "@3833");
    Station albiDel = new Station("Albi Sq. Delmas", "@3834");
    Station blagTraf = new Station("Blagnac Aéroport Côté Trafic", "@3835");
    Station toulJacqu = new Station("Toulouse Jacquier", "@3836");
    Station tarbes = new Station("Tarbes J. Dupuy", "@3837");
    Station montau = new Station("Montauban Farguettes", "@8603");
    Station peyr = new Station("Peyrusse-Vieille", "@3838");
    Station pauTourasse = new Station("Tourasse, Pau, Aquitaine", "@5069");

    private Station[] stations = {toulMaz, toulBer, toulRAlbi, blagPiste, lourdes, toulZIChap, toulEizen, albiDel, blagTraf, toulJacqu, tarbes, montau, peyr, pauTourasse};
    int nbStations = stations.length;

    private String[] nomsStations = new String[nbStations];

    AutoCompleteTextView choixVille;
    Button validerB;

    String idSelected;

    float pollution;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution);


        for(int i = 0 ; i<nbStations ; i++){
            nomsStations[i] = stations[i].getNom();
        }

        choixVille = (AutoCompleteTextView) findViewById(R.id.choixVille);
        validerB = (Button) findViewById(R.id.valider);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, nomsStations);

        AutoCompleteTextView actv =  (AutoCompleteTextView)findViewById(R.id.choixVille);
        actv.setThreshold(1);
        actv.setAdapter(adapter);

        textViewResult = findViewById(R.id.results);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.waqi.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    }

    private void getData(){

        Call<APIResponse> call = jsonPlaceHolderApi.data(idSelected);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText(("Error response :" + response.code()));
                    return;
                }

                APIResponse data = response.body();
                String content = "";
                content += data.data().aqi();
                pollution = Float.parseFloat(content);
                textViewResult.append(content);
                content ="";
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                textViewResult.setText((t.getMessage()));
            }
        });
    }

    public void onClickValider(View v){

        for(int i = 0 ; i<nbStations ; i++){
            if(stations[i].getNom().equals(choixVille.getText().toString())){
                idSelected = stations[i].getId();
            }
        }

        getData();

        if(pollution <50.0){
            textViewResult.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, "Vert", Toast.LENGTH_SHORT).show();
        }
        else if(pollution<100.0){
            textViewResult.setBackgroundColor(Color.YELLOW);
            Toast.makeText(this, "Jaune", Toast.LENGTH_SHORT).show();
        }
        else if(pollution <150){
            textViewResult.setBackgroundColor(Color.parseColor("#f39c12"));
        }
        else if(pollution <100){
            textViewResult.setBackgroundColor(Color.RED);
        }
        else if(pollution <100){
            textViewResult.setBackgroundColor(Color.parseColor(" #8e44ad "));
        }
        else if(pollution <100){
            textViewResult.setBackgroundColor(Color.parseColor("#641e16"));
        }

    }

}

