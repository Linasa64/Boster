package com.example.boster;

//import static com.example.boster.MainActivity.db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Saisie extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);
        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);
        db = bdd.getWritableDatabase();
    }

    protected void onDestroy(Bundle savedInstanceState){
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickMenu(){
        Intent intent = new Intent(Saisie.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisie(){
        Intent intent = new Intent(Saisie.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistorique(){
        Intent intent = new Intent(Saisie.this, Historique.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisieB(View v){
        Intent intent = new Intent(Saisie.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistoriqueB(View v){
        Intent intent = new Intent(Saisie.this, Historique.class);
        startActivity(intent);
        finish();
    }

    public void ajouterDB(String mode, int distance, String ville){
        ContentValues values = new ContentValues();
        //values.put("id", 4);
        values.put("mode", mode);
        values.put("distance", distance);
        values.put("ville", ville);
        //values.put("date", );

        db.insert("Deplacements", null, values);
    }

    public void onClickValider(View v){

        String mode = "Envoiture";

        int distance;
        String ville;

        //boolean checked = ((RadioButton) v).isChecked();

        RadioGroup rbg = (RadioGroup) findViewById(R.id.mode);
        int checkedRb = rbg.getCheckedRadioButtonId();

        switch(checkedRb) {
            case R.id.radioButton_Pied:
                mode = "Apied";
                break;
            case R.id.radioButton_Velo:
                mode = "Avelo";
                break;
        }

        Log.v("Test", mode);

        distance = Integer.parseInt(((EditText) findViewById(R.id.distance)).getText().toString());

        ville = ((EditText) findViewById(R.id.ville)).getText().toString();

        Log.v("Test", ville);

        ajouterDB(mode, distance, ville);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Accueil:
                onClickMenu();
                break;
            case R.id.Saisir:
                Toast.makeText(this, "Déjà sur la page de saisie", Toast.LENGTH_SHORT).show();
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
}