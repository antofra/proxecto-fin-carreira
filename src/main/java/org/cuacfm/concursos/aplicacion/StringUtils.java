package org.cuacfm.concursos.aplicacion;

import java.text.Normalizer;
import java.text.Normalizer.Form;

public class StringUtils {
    public static String normalizeString(String str) {
        return Normalizer.normalize(str, Form.NFC).replaceAll("[^\\p{ASCII}]", "").toUpperCase().replace(" ", "_");
    }
}
