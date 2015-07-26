package fr.valquev.tec.objects;

import android.content.Context;
import android.content.SharedPreferences;

import fr.valquev.tec.Utils;

public class Network {

    private int id;
    private String ville;
    private String network;
    private String network_map;
    private String base_url;
    private String external_code;

    private SharedPreferences sp;

    public Network(Context context) {
        sp = context.getSharedPreferences(Utils.PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isSet() {
        return !sp.getString(Utils.PREF_NETWORK, "").equals("");
    }

    public String getBase_url() {
        return base_url;
    }

    public String getExternal_code() {
        return external_code;
    }

    public int getId() {
        return id;
    }

    public String getNetwork() {
        return network;
    }

    public String getNetwork_map() {
        return network_map;
    }

    public String getVille() {
        return ville;
    }
}
