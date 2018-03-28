package com.example.haycard.pizzeria;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

/*
Choix des ingrédients

 */
public class Ingredient extends Fragment implements View.OnClickListener{


    private Button mozza;
    private Button gorgonzola;
    private Button anchois;
    private Button capres;
    private Button olive;
    private Button artichaut;
    private Button jambonCru;
    private Button jambonCuit;
    private Button valider;
    private String personnaliseCommande;
    private String numTable;
    private List<Button> ListeButtonValider = new ArrayList<>();
    private List<Button> ListeButton = new ArrayList<>();
    private List<Boolean> buttonBool = new ArrayList<>();
    public Ingredient() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);

        TextView table = (TextView)getActivity().findViewById(R.id.Numero);
        numTable = table.getText().toString();
        personnaliseCommande = numTable;


        mozza = (Button) v.findViewById(R.id.mozzarela);
        gorgonzola = (Button) v.findViewById(R.id.gorgonzola);
        anchois = (Button) v.findViewById(R.id.anchois);
        capres = (Button) v.findViewById(R.id.capres);
        olive = (Button) v.findViewById(R.id.olives);
        artichaut = (Button) v.findViewById(R.id.artichauts);
        jambonCru = (Button) v.findViewById(R.id.jambonCru);
        jambonCuit = (Button) v.findViewById(R.id.jambonCuit);
        valider = (Button) v.findViewById(R.id.valider);

        ListeButton.add(mozza);
        ListeButton.add(gorgonzola);
        ListeButton.add(anchois);
        ListeButton.add(capres);
        ListeButton.add(olive);
        ListeButton.add(artichaut);
        ListeButton.add(jambonCru);
        ListeButton.add(jambonCuit);
        ListeButton.add(valider);

        for(int i =0; i<ListeButton.size(); i++){
            ListeButton.get(i).setOnClickListener(this);
            buttonBool.add(false);
        }


        return v;
    }

    public void fragementChange(){
        Commande frag = new Commande();
        Bundle bundle = new Bundle();
        bundle.putString("message",personnaliseCommande);
        frag.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, frag);
        transaction.commit();
    }

    // Application des préférences lorsque le fragment devient visible
    @Override
    public void onResume() {
        super.onResume();
        applyPref();
    }


    protected  void applyPref(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean couleur = sharedPref.getBoolean(String.valueOf(getResources().getText(R.string.COULEUR)), true);
        for(int i=0; i<ListeButton.size(); i++){
            if(couleur){
                ListeButton.get(i).setBackgroundColor(Color.LTGRAY);

            }else{
                ListeButton.get(i).setBackgroundColor(Color.WHITE);

            }
        }

    }

    public void afficherList(){
        String txt = "";
        for(int i =0; i<ListeButtonValider.size(); i++){
            txt += ListeButtonValider.get(i) + " + ";
        }
        System.out.println(txt);
    }

    public void alert(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes
                        for(int i=0; i<ListeButtonValider.size(); i++){
                            personnaliseCommande += ListeButtonValider.get(i).getText().toString() + " + ";
                        }
                        fragementChange();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No
                        personnaliseCommande = numTable;
                        ListeButtonValider.clear();
                        for(int i =0; i<buttonBool.size(); i++){
                            buttonBool.set(i, false);
                        }
                        applyPref();
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        String list = "";
        for (int i = 0; i<ListeButtonValider.size(); i++){
            list += ListeButtonValider.get(i).getText().toString() + " ";
        }
        ab.setMessage("Do you want to order a pizza with " + list + "?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void check(Button btn){
        afficherList();
        int index = 0;
        while (btn.getId()!=ListeButton.get(index).getId()){
            index++;
        }

        System.out.println(buttonBool.get(index));
        if(buttonBool.get(index)){
            int index2 = 0;
            while (ListeButtonValider.get(index2).getId() != btn.getId()){
                index2++;
            }
            ListeButtonValider.remove(index2);
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean couleur = sharedPref.getBoolean(String.valueOf(getResources().getText(R.string.COULEUR)), true);
            if(couleur){
                btn.setBackgroundColor(Color.LTGRAY);
            }else{
                btn.setBackgroundColor(Color.WHITE);
            }
            buttonBool.set(index, false);
        }else{
            System.out.println("rouge");
            ListeButtonValider.add(btn);
            btn.setBackgroundColor(Color.RED);
            buttonBool.set(index, true);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.valider){
           alert();

        }else if(view.getId() == R.id.mozzarela){
            check(mozza);
            /*ListeButtonValider.add(mozza);
            personnaliseCommande += ListeButtonValider.get(ListeButtonValider.size()-1).getText().toString();
            //Choix des ingrédients
            mozza.setBackgroundColor(Color.RED);*/
        }else if(view.getId() == R.id.gorgonzola){
            check(gorgonzola);
        }else if(view.getId() == R.id.anchois){
            check(anchois);
        }else if(view.getId() == R.id.olives){
            check(olive);
        }else if(view.getId() == R.id.capres){
            check(capres);
        }else if(view.getId() == R.id.artichauts){
            check(artichaut);
        }else if(view.getId() == R.id.jambonCru){
            check(jambonCru);
        }else if(view.getId() == R.id.jambonCuit){
            check(jambonCuit);
        }
    }
}
