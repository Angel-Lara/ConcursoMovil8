package mx.edu.itchetumal.concursomovil8;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.SimpleAdapter;

import org.apache.http.HttpEntity;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PC-04 on 02/12/2015.
 */
public class busquedas extends Fragment {
    String bus_uno = "info_Servicio_Restaurant.php";
    String bus_dos = "info_Servicio_Taxi.php";
    String bus_tres = "info_Servicio_Gym.php";
    String bus_cuatro = "info_Servicio_Despertador.php";
    String bus_cinco = "info_Servicio_Salida.php";

    Handler handler = new Handler();
static HttpEntity entity;
    InputStream is = null;
   static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
  static SimpleAdapter datos;
    String obs="";
    String fecha="";
    String hora="";
    static ProgressDialog pDialog;
    HashMap<String, String> blogPost = new HashMap<String, String>();



    JSONObject jsonobject;


    public void tutorial(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.context);
        builder.setTitle("Información");

        builder.setMessage("Este boton sirve para actualizar los datos propuestos por el usuario." + "\n" + "Presione Ok para Continuar!!!!!");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface dialogInterface, int i) {

                    }
                });
        Dialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
    public void progresBar(){
        //para el progress dialog
        pDialog = new ProgressDialog(HomePage.context);
        pDialog.setMessage("Buscando Datos....\n Espere unos segundos....." );
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    public void cerrar(){
        pDialog.dismiss();
    }
}
