package com.example.boster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Historique extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);
        db = bdd.getWritableDatabase();

        ListView liste = (ListView) findViewById(R.id.listView);

        //String[] valeurs = {getContentDB(), "Mode", "Ville"};
        String[] valeurs = getContentDB();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valeurs);

        liste.setAdapter(adapter);

    }

    @SuppressLint("Range")
    public String[] getContentDB(){

        int numRows = (int) DatabaseUtils.queryNumEntries(db, "Deplacements");
        int cursorRows = 0;

        String [] result = new String[numRows];

        String[] col = {"distance", "mode", "ville"};
        String [] select = {};

        Cursor cursor = db.query("Deplacements", col, "", select, null, null, "id DESC");

        if(cursor.moveToFirst()){
            String[] columnNames = cursor.getColumnNames();
            do{
                for(String name : columnNames){
                    if(result[cursorRows]==null){
                        result[cursorRows]="";
                    }
                    result[cursorRows] = result[cursorRows]+String.format("%s : %s\n", name, cursor.getString(cursor.getColumnIndex(name)));
                }
                cursorRows++;
            }while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickMenu(){
        Intent intent = new Intent(Historique.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisie(){
        Intent intent = new Intent(Historique.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistorique(){
        Intent intent = new Intent(Historique.this, Historique.class);
        startActivity(intent);
        finish();
    }

    public void onClickSaisieB(View v){
        Intent intent = new Intent(Historique.this, Saisie.class);
        startActivity(intent);
        finish();
    }

    public void onClickHistoriqueB(View v){
        Intent intent = new Intent(Historique.this, Historique.class);
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

}