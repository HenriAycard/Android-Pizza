package com.example.haycard.pizzeria;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

//import com.example.haycard.pizzeria.R;

//import com.example.haycard.pizzeria.R;

/**
 * Created by henri.aycard on 27/03/2018.
 */

    public class Prefs extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Affichage à partir du fichier XML
        addPreferencesFromResource(R.xml.prefs);
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        Commande frag = new Commande();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, frag);
        transaction.commit();
    }
*/
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        ((MainActivity) getActivity()).applyPref();

    }

    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }


}
