package com.example.haycard.pizzeria;
/*
 Partie 3 - Animation de l'interface
*/

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Commande extends Fragment implements View.OnClickListener{

    //Partie 3 - Animation de l'interface
    //Animation
    private Button napolitaine;
    private Button royale;
    private Button quatre_fromage;
    private Button montagnarde;
    private Button raclette;
    private Button hawai;
    private Button pasta;
    private Button tiramisu;
    private Button personnalisee;

    private ArrayList<Button> listButton = new ArrayList<>();
    private  ArrayList<String> ListeNom = new ArrayList<>();
    private ArrayList<Integer> cpt = new ArrayList<>();
    private final static String Mycpt = "Mycpt";
    private String text;
    private String msgSend;


   public Commande() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_commande, container, false);
        super.onCreate(savedInstanceState);
        //Partie 3 - Animation de l'interface
        //Animation
        //Récupérez les objets de l'interface (les 8 boutons) dans la redéfinition de la méthode onCreate.
        napolitaine = (Button) v.findViewById(R.id.napolitaine);
        royale = (Button) v.findViewById(R.id.royale);
        quatre_fromage = (Button) v.findViewById(R.id.quatreFromage);
        montagnarde = (Button) v.findViewById(R.id.montagnarde);
        raclette = (Button) v.findViewById(R.id.raclette);
        hawai = (Button) v.findViewById(R.id.hawai);
        pasta = (Button) v.findViewById(R.id.pannaCotta);
        tiramisu = (Button) v.findViewById(R.id.tiramisu);
        personnalisee = (Button) v.findViewById(R.id.personnalisee);

        //Partie 3 - Animation de l'interface
        //Persistance courte
        /*name.add(napolitaine.getText().toString());
        name.add(royale.getText().toString());
        name.add(quatre_fromage.getText().toString());
        name.add(montagnarde.getText().toString());
        name.add(raclette.getText().toString());
        name.add(hawai.getText().toString());
        name.add(pasta.getText().toString());
        name.add(tiramisu.getText().toString());
        name.add(personnalisee.getText().toString());*/
        listButton.add(napolitaine);
        listButton.add(royale);
        listButton.add(quatre_fromage);
        listButton.add(montagnarde);
        listButton.add(raclette);
        listButton.add(hawai);
        listButton.add(pasta);
        listButton.add(tiramisu);
        listButton.add(personnalisee);

        //Partie 3 - Animation de l'interface
        //Animation
        //Associez un écouteur aux 8 boutons.
        for(int i = 0; i<listButton.size(); i++){
            ListeNom.add(listButton.get(i).getText().toString());
            listButton.get(i).setOnClickListener(this);
        }

        /*
        napolitaine.setOnClickListener(this);
        royale.setOnClickListener(this);
        quatre_fromage.setOnClickListener(this);
        montagnarde.setOnClickListener(this);
        raclette.setOnClickListener(this);
        hawai.setOnClickListener(this);
        pasta.setOnClickListener(this);
        tiramisu.setOnClickListener(this);
        personnalisee.setOnClickListener(this);*/


        System.out.print("rtenzdnvsnvnls");
        //Persistance courte
        if (savedInstanceState != null) {
            cpt = savedInstanceState.getIntegerArrayList(Mycpt);
            text ="";

            for (int i = 0; i<ListeNom.size(); i++){
                text = ListeNom.get(i) + ": " + cpt.get(i);
                listButton.get(i).setText(text);
            }

        } else {
            //Persistance courte
            for (int i = 0; i < 9; i++) {
                cpt.add(0);
            }
        }
        Bundle arg = getArguments();
        if(arg != null){
            text= ListeNom.get(8) + ": " + cpt.get(8);
            personnalisee.setText(text);
            WaitingThread w = new WaitingThread();
            msgSend = getArguments().getString("message");
            w.execute();
        }
        /*
        Ingredient frag = new Ingredient();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, frag);
        transaction.commit();*/

        return v;
    }



    //Partie 3 - Animation de l'interface
    //Implantez la méthode public void onClick(View v) de l'écouteur pour passer une commande du plat correspondant.
    @Override
    public void onClick(View v) {
        WaitingThread w = new WaitingThread();
        TextView table = (TextView)getActivity().findViewById(R.id.Numero);
        String NumTable = (String)table.getText();
        String text = "";
        if (v.getId() == R.id.napolitaine) {
            cpt.set(0, cpt.get(0) + 1);
            text = ListeNom.get(0) + ": " + cpt.get(0);
            napolitaine.setText(text);
            msgSend = NumTable + ListeNom.get(0);
            w.execute();

        } else if (v.getId() == R.id.royale) {
            cpt.set(1, cpt.get(1) + 1);
            text = ListeNom.get(1) + ": " + cpt.get(1);
            royale.setText(text);
            msgSend = NumTable + ListeNom.get(1);
            w.execute();

        } else if (v.getId() == R.id.quatreFromage) {
            cpt.set(2, cpt.get(2) + 1);
            text = ListeNom.get(2) + ": " + cpt.get(2);
            quatre_fromage.setText(text);
            msgSend = NumTable + ListeNom.get(2);
            w.execute();

        } else if (v.getId() == R.id.montagnarde) {
            cpt.set(3, cpt.get(3) + 1);
            text = ListeNom.get(3) + ": " + cpt.get(3);
            montagnarde.setText(text);
            msgSend = NumTable + ListeNom.get(3);
            w.execute();

        } else if (v.getId() == R.id.raclette) {
            cpt.set(4, cpt.get(4) + 1);
            text = ListeNom.get(4) + ": " + cpt.get(4);
            raclette.setText(text);
            msgSend = NumTable + ListeNom.get(4);
            w.execute();

        } else if (v.getId() == R.id.hawai) {
            cpt.set(5, cpt.get(5) + 1);
            text = ListeNom.get(5) + ": " + cpt.get(5);
            hawai.setText(text);
            msgSend = NumTable + ListeNom.get(5);
            w.execute();

        } else if (v.getId() == R.id.pannaCotta) {
            cpt.set(6, cpt.get(6) + 1);
            text = ListeNom.get(6) + ": " + cpt.get(6);
            pasta.setText(text);
            msgSend = NumTable + ListeNom.get(6);
            w.execute();

        } else if (v.getId() == R.id.tiramisu) {

            cpt.set(7, cpt.get(7) + 1);
            text = ListeNom.get(7) + ": " + cpt.get(7);
            tiramisu.setText(text);
            msgSend = NumTable + ListeNom.get(7);
            w.execute();

        } else if(v.getId() == R.id.personnalisee){
            cpt.set(8, cpt.get(8) + 1);
            text = ListeNom.get(8) + ": " + cpt.get(8);
            personnalisee.setText(text);

            Ingredient frag = new Ingredient();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, frag);
            transaction.commit();
        }
    }

    //Partie 3 - Animation de l'interface
    //Persistance courte
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(Mycpt,cpt);
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
        for(int i=0; i<listButton.size(); i++){
            if(couleur){
                listButton.get(i).setBackgroundColor(Color.LTGRAY);

            }else{
                listButton.get(i).setBackgroundColor(Color.WHITE);

            }
        }

    }



    private class WaitingThread extends AsyncTask<String, String, String> {
        private String message;
        private String msgRetour;
        private TextView MyText = (TextView) getActivity().findViewById(R.id.Numero);
        //private Integer cpt = 0;
        @Override
        protected String doInBackground(String... strings) {
            try {
                Socket socket = new Socket("chadok.info", 9874);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                writer.write(msgSend);
                //writer.close();
                writer.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = reader.readLine();
                publishProgress(message);
                //onProgressUpdate();

                BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                /*while ((msgRetour = reader2.readLine()) == null){
                    //.cpt++;
                }*/
                //onPostExecute("message");
                msgRetour = reader2.readLine();
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
            //publishProgress(message);
            MyText.setText(message);
        }

        @Override
        protected void onPostExecute(String s) {
           // super.onPostExecute(s);
            MyText.setText(msgRetour);
        }
    }

}
