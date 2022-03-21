package com.example.boster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixVilleApi extends AppCompatActivity {

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
    Station dieppe = new Station("Dieppe, avenue Gambetta, Trafic", "@10088");

    private Station[] stations = {toulMaz, toulBer, toulRAlbi, blagPiste, lourdes, toulZIChap, toulEizen, albiDel, blagTraf, toulJacqu, tarbes, montau, peyr, pauTourasse, dieppe};
    int nbStations = stations.length;
    private String[] nomsStations = new String[nbStations];

    AutoCompleteTextView choixVille;
    Button validerB;
    String idSelected;
    String nomStation;

    float pollution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_ville_api);

        for(int i = 0 ; i<nbStations ; i++){
            nomsStations[i] = stations[i].getNom();
        }

        choixVille = (AutoCompleteTextView) findViewById(R.id.choixVille2);
        validerB = (Button) findViewById(R.id.valider);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, nomsStations);

        AutoCompleteTextView actv =  (AutoCompleteTextView)findViewById(R.id.choixVille2);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
    }

    public void onClickSelectionner(View v){

        for(int i = 0 ; i<nbStations ; i++){
            if(stations[i].getNom().equals(choixVille.getText().toString())){
                idSelected = stations[i].getId();
                nomStation = stations[i].getNom();
            }
        }

        Intent intentRetour = new Intent();
        intentRetour.putExtra("idStation", idSelected);
        intentRetour.putExtra("nomStation", nomStation);

        this.setResult(Activity.RESULT_OK, intentRetour);
        this.finish();
    }

}