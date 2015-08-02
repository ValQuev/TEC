package fr.valquev.tec.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.valquev.tec.R;
import fr.valquev.tec.Utils;
import fr.valquev.tec.objects.Network;

public class TransportsEnCommun extends AppCompatActivity {

    private Context mContext;
    private Network network;

    //TODO NavigationDrawer d'où on recherche le réseau de transports et Tabs pour les différentes fonctionnalités

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transports_en_commun);

        mContext = this;

        network = new Network(mContext);

        if(network.isSet()) {
            //TODO On lance l'appli
        } else {
            startActivity(new Intent(mContext, SelectNetwork.class));
        }
    }
}