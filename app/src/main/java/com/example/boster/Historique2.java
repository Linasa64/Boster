package com.example.boster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Historique2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        List<Deplacement> deplacementDetail = getListData();

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

    private List<Deplacement> getListData(){
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
    }
}