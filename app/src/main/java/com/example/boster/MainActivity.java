package com.example.boster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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