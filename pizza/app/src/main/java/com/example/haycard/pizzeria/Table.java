package com.example.haycard.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/*
Partie 4 - Choix de la table du restaurant
 */
public class Table extends AppCompatActivity implements View.OnClickListener{
    public final static String RESULTAT_SAUVERGARDE = "TABLE";
    private Button btnValider;
    private String NumTable;
    private EditText table;
    public static final String RES = "RES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        btnValider = (Button) findViewById(R.id.BtnValider);
        btnValider.setOnClickListener(this);
        //EditText e : String.valueOf(e.getText()).
        table = (EditText) findViewById(R.id.numTable);


        if (savedInstanceState != null) {
            NumTable = savedInstanceState.getString(RESULTAT_SAUVERGARDE);
        }
    }

    // Application des préférences lorsque le fragment devient visible
    @Override
    public void onResume() {
        super.onResume();
        applyPref();
    }


    protected  void applyPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean couleur = sharedPref.getBoolean(String.valueOf(getResources().getText(R.string.COULEUR)), true);

        if (couleur) {
            btnValider.setBackgroundColor(Color.LTGRAY);

        } else {
            btnValider.setBackgroundColor(Color.WHITE);

        }
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.BtnValider) {
            NumTable = String.valueOf(table.getText());

            Intent i = new Intent( this, MainActivity.class );
            //Log.v("TAG", NumTable);
            i.putExtra(RES, NumTable);
            startActivity(i);
        }
    }
}
