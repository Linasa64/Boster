package com.example.boster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Historique2 extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);
        db = bdd.getWritableDatabase();

        List<Deplacement> deplacementDetail = getContentDB();

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, deplacementDetail));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> aV, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Deplacement deplacement = (Deplacement) o;
                Toast.makeText(Historique2.this, deplacement.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickMenu(){
        Intent intent = new Intent(Historique2.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisie(){
        Intent intent = new Intent(Historique2.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistorique(){
        Intent intent = new Intent(Historique2.this, Historique2.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisieB(View v){
        Intent intent = new Intent(Historique2.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistoriqueB(View v){
        Intent intent = new Intent(Historique2.this, Historique2.class);
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
                Toast.makeText(this, R.string.toastHistorique, Toast.LENGTH_SHORT).show();
                break;
            case R.id.Quitter:
                System.exit(0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("Range")
    public List<Deplacement> getContentDB(){

        List<Deplacement> resultList = new ArrayList<Deplacement>();

        String[] col = {"mode", "date", "ville", "distance"};
        String [] select = {};

        Cursor cursor = db.query("Deplacements", col, "", select, null, null, "id DESC");

        if(cursor.moveToFirst()){
            String[] columnNames = cursor.getColumnNames();
            do{
                Deplacement d = new Deplacement(cursor.getString(cursor.getColumnIndex("mode")), cursor.getString(cursor.getColumnIndex("date")), cursor.getString(cursor.getColumnIndex("ville")), cursor.getString(cursor.getColumnIndex("distance")), "");
                Float kgCo2 = Float.parseFloat(d.getDistance())*0.126F;
                d.setCo2(String.format("%.02f", kgCo2));
                resultList.add(d);
            }while (cursor.moveToNext());
        }
        return resultList;
    }

}