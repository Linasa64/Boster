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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    static ClientDbHelper bdd;
//
//    static SQLiteDatabase db = bdd.getWritableDatabase();

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);

        db = bdd.getWritableDatabase();

        final TextView qteCO2Voiture = (TextView) findViewById(R.id.nbVoiture);
        qteCO2Voiture.setText(getCO2Diesel(getContentDBIntoCO2()));
        final TextView qteCO2Moto = (TextView) findViewById(R.id.nbMoto);
        qteCO2Moto.setText(getCO2Essence(getContentDBIntoCO2()));

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
        Intent intent = new Intent(MainActivity.this, Historique2.class);
        startActivity(intent);
        //finish();
    }

    public void onClickPollutionB(View view) {
        Intent intent = new Intent(MainActivity.this, Pollution.class);
        startActivity(intent);
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

    @SuppressLint("Range")
    public int getContentDBIntoCO2(){

        int resultBD = 0;

        String[] col = {"distance"};
        String [] select = {};

        Cursor cursor = db.query("Deplacements", col, "", select, null, null, "id DESC");

        if(cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();
            do {
                resultBD += Integer.parseInt(cursor.getString(cursor.getColumnIndex("distance")));
            } while (cursor.moveToNext());
        }

        return resultBD;
    }

    public String getCO2Diesel(int distance){
        return String.valueOf(String.format("%.3f", distance*0.132));
    }

    public String getCO2Essence(int distance){
        return String.valueOf(String.format("%.3f", distance*0.120));
    }

}