package com.example.boster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    static ClientDbHelper bdd;
//
//    static SQLiteDatabase db = bdd.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);
        SQLiteDatabase db = bdd.getWritableDatabase();

        //DATABASE


//        ContentValues values = new ContentValues();
//        values.put("id", 1);
//        values.put("mode", "velo");
//        values.put("distance", 10);
//        values.put("ville", "lourdios");
        //values.put("date", );

        //db.insert("Deplacements", null, values);

        //db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickSaisie(){
        Intent intent = new Intent(MainActivity.this, Saisie.class);
        startActivity(intent);
        //finish();
    }

    public void onClickHistorique(){
        Intent intent = new Intent(MainActivity.this, Historique.class);
        startActivity(intent);
        //finish();
    }

    public void onClickSaisieB(View v){
        Intent intent = new Intent(MainActivity.this, Saisie.class);
        startActivity(intent);
        //finish();
    }

    public void onClickHistoriqueB(View v){
        Intent intent = new Intent(MainActivity.this, Historique.class);
        startActivity(intent);
        //finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Accueil:
                Toast.makeText(this, "Déjà sur la page d'accueil", Toast.LENGTH_SHORT).show();
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

}