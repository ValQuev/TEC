package fr.valquev.tec;

import java.text.Normalizer;

public class Utils {

    public static String stringNormalizer(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

}
