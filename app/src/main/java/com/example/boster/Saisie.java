package com.example.boster;

//import static com.example.boster.MainActivity.db;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Saisie extends AppCompatActivity {

    SQLiteDatabase db;

    TextView distanceTV;
    TextView villeTV;
    RadioGroup rbg;
    Button validerB;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);
        ClientDbHelper bdd;
        bdd = new ClientDbHelper(this);
        db = bdd.getWritableDatabase();

        distanceTV = (TextView) findViewById(R.id.distance);
        villeTV = (TextView) findViewById(R.id.ville);
        rbg = (RadioGroup) findViewById(R.id.mode);
        validerB = (Button) findViewById(R.id.valider);
        validerB.setEnabled(false);

        distanceTV.addTextChangedListener(textWatcher);
        villeTV.addTextChangedListener(textWatcher);

        rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkFieldsForEmptyValues();
            }
        });
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
        Intent intent = new Intent(Saisie.this, Historique2.class);
        startActivity(intent);
        finish();
    }

    public void ajouterDB(String mode, int distance, String ville){
        ContentValues values = new ContentValues();
        //values.put("id", 4);
        values.put("mode", mode);
        values.put("distance", distance);
        values.put("ville", ville);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        values.put("date", dateFormat.format(Calendar.getInstance().getTime()));

        db.insert("Deplacements", null, values);
    }

    public void onClickValider(View v){

        String mode = null;

        int distance;
        String ville = null;

        //boolean checked = ((RadioButton) v).isChecked();

        RadioGroup rbg = (RadioGroup) findViewById(R.id.mode);
        int checkedRb = rbg.getCheckedRadioButtonId();

        switch(checkedRb) {
            case R.id.radioButton_Velo:
                mode = "Velo";
                break;
            case R.id.radioButton_Pied:
                mode = "Marche";
                break;
        }

        //Log.v("Test", mode);

        distance = Integer.parseInt(((EditText) findViewById(R.id.distance)).getText().toString());

        ville = ((EditText) findViewById(R.id.ville)).getText().toString();

        ajouterDB(mode, distance, ville);
        Toast.makeText(this, R.string.trajetEnregistré, Toast.LENGTH_SHORT).show();
        finish();

    }

    private void checkFieldsForEmptyValues(){
        String s1 = distanceTV.getText().toString().trim();
        String s2 = villeTV.getText().toString().trim();
        int s3 = rbg.getCheckedRadioButtonId();

        if(!s1.isEmpty()&&!s2.isEmpty()&&(s3==R.id.radioButton_Velo ||s3==R.id.radioButton_Pied)){
            validerB.setEnabled(true);
        }
        else{
            validerB.setEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.Accueil:
                onClickMenu();
                break;
            case R.id.Saisir:
                Toast.makeText(this, R.string.toastSaisir, Toast.LENGTH_SHORT).show();
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