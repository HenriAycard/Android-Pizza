package com.example.haycard.pizzeria;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    private String NumTable;
    private TextView MyText;
    PreferenceFragment prefFrag = new Prefs();
    Fragment frag = new Commande();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        NumTable = intent.getStringExtra(Table.RES);
        MyText = (TextView) findViewById(R.id.Numero);
        char[] c_arr = NumTable.toCharArray();
        if(c_arr.length==1){
            NumTable = "0"+NumTable;
        }
        MyText.setText(NumTable);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, frag);
        transaction.commit();
    }

    // Application des préférences lorsque l'activité devient visible
    @Override
    protected void onResume() {
        super.onResume();
        applyPref();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // L'item sur lequel l'utilisateur a cliqué
        int id = item.getItemId();

        // Action choisie selon l'item
        if (id == R.id.action_settings) {
            // Attachement du fragment de préférences
            getFragmentManager().beginTransaction().addToBackStack("pref").replace(R.id.fragment, prefFrag).commit();
            //getFragmentManager().beginTransaction().replace(android.R.id.content, new Prefs()).addToBackStack("pref").commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected  void applyPref(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean couleur = sharedPref.getBoolean(String.valueOf(getResources().getText(R.string.COULEUR)), true);


    }


}
