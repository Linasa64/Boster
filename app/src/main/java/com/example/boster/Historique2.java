package com.example.boster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

    @SuppressLint("Range")
    public List<Deplacement> getContentDB(){

        List<Deplacement> resultList = new ArrayList<Deplacement>();

        String[] col = {"mode", "date", "ville", "distance"};
        String [] select = {};

        Cursor cursor = db.query("Deplacements", col, "", select, null, null, "id DESC");

        if(cursor.moveToFirst()){
            String[] columnNames = cursor.getColumnNames();
            do{
                Deplacement d = new Deplacement(cursor.getString(cursor.getColumnIndex("mode")), cursor.getString(cursor.getColumnIndex("date")), cursor.getString(cursor.getColumnIndex("ville")), cursor.getString(cursor.getColumnIndex("distance")), "xxxx");
                resultList.add(d);
            }while (cursor.moveToNext());
        }
        return resultList;
    }

}