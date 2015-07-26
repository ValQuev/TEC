package fr.valquev.tec;

import java.text.Normalizer;

public class Utils {

    public static String PREF_NAME = "TransportsEnCommun";
    public static String PREF_NETWORK = "prefNetwork";

    public static String NETWORKS_URL = "http://192.168.1.78/~ValQuev/quevit.fr/applis/tec/citynetworks.php";

    public static String removeAccent(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

}
