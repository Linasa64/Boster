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

        //int numRows = (int) DatabaseUtils.queryNumEntries(db, "Deplacements");
        //int cursorRows = 0;

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

/*    private List<Deplacement> getListData(){
        List<Deplacement> list = new ArrayList<Deplacement>();
        Deplacement a = new Deplacement("16/03/22", "10", "0.0235");
        Deplacement b = new Deplacement("17/03/22", "11", "0.0236");
        Deplacement c = new Deplacement("18/03/22", "12", "0.0237");
        Deplacement d = new Deplacement("19/03/22", "13", "0.0238");

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        return list;
    }*/
}