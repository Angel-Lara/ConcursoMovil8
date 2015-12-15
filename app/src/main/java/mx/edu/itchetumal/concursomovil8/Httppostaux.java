package mx.edu.itchetumal.concursomovil8;

/**
 * Created by PC-04 on 19/11/2015.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*CLASE AUXILIAR PARA EL ENVIO DE PETICIONES A NUESTRO SISTEMA
 * Y MANEJO DE RESPUESTA.*/
public class Httppostaux {

    InputStream is = null;
    String result = "";


    public void getpostresponse(InputStream dato) {

        // Convierte respuesta a String
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            result = sb.toString();
            getjsonarray(result);
            Log.e("getpostresponse", " result= " + sb.toString());
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
    }

    public JSONArray getjsonarray(String a) {
        // parse json data
        try {
            JSONArray jArray = new JSONArray(a);
            Log.e("log_tag", "Error parsing data " + jArray );
            return jArray;
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            return null;
        }

    }

}
