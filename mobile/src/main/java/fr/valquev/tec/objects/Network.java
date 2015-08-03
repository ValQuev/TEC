package fr.valquev.tec.objects;

import android.content.Context;
import android.content.SharedPreferences;

import fr.valquev.tec.Utils;

public class Network {

    public static final String PREF_NAME = "TransportsEnCommun";
    public static final String PREF_NETWORK = "prefNetwork";

    public static final String NETWORKS_URL = "http://quevit.fr/applis/tec/citynetworks2.php";

    private int id;
    private String ville;
    private String network;
    private String network_map;
    private String base_url;
    private String external_code;

    private SharedPreferences sp;

    public Network(Context context) {
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void regist() {
        //sp.edit().putString(Utils.PREF_NETWORK, this.toString()).apply();
    }

    public boolean isSet() {
        return !sp.getString(PREF_NETWORK, "").equals("");
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
