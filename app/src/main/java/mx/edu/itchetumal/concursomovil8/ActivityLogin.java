package mx.edu.itchetumal.concursomovil8;

/**
 * Created by centroinnovacion3 on 13/11/15.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ActivityLogin extends Activity  implements OnClickListener{
     EditText username;
     EditText password;
     Button iniciar;
     String user,pass;

    Httppostaux post;

    Handler handler = new Handler();

    String URL_connect="http://192.168.10.103/Farol_rojo/login.php";


    static	ProgressDialog pDialog ;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //obteniendo una instancia
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        //obteniendo una instancia
        iniciar = (Button)findViewById(R.id.btnEntrar);
        //Registrando el escuchador
        iniciar.setOnClickListener(this);



    }

    @Override

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:

                user= username.getText().toString();
                pass = password.getText().toString();
                    if(user.equals("") || pass.equals("") ){
                        Toast.makeText(getApplicationContext(),"Verificar Usuario o contraseña",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Cargando datos",Toast.LENGTH_LONG).show();
                        coneccion();
                    }
            break;
        }
    }


public void Thread_log() {
    //para el progress dialog
    pDialog = new ProgressDialog(ActivityLogin.this);
    pDialog.setMessage("Autenticando....");
    pDialog.setIndeterminate(false);
    pDialog.setCancelable(true);
    pDialog.show();
    new Thread(new Runnable() {
        @Override
        public void run() {

                    try {
                        Thread.sleep(3000);
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost(URL_connect);
                        List<NameValuePair> imagenes = new ArrayList<NameValuePair>();
                        imagenes.add(new BasicNameValuePair("us", user));
                        imagenes.add(new BasicNameValuePair("con", pass));

                        httppost.setEntity(new UrlEncodedFormEntity(imagenes));
                        HttpResponse responses = httpclient.execute(httppost);
                        HttpEntity entity = responses.getEntity();
                        String text = EntityUtils.toString(entity);
                        Log.e("Mensajito:", " " + text);

                        if (text.equalsIgnoreCase("true")) {
                            pDialog.dismiss();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Verifique su Usuario y Contraseña",Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {

                            pDialog.dismiss();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {


                                    Toast.makeText(getApplicationContext(), "Bienvenido: "+" "+user, Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    i.putExtra("USUARIO",user);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

    }).start();

}









    public void coneccion(){
         WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
            boolean wifiEnabled = wifiManager.isWifiEnabled();
            if (!wifiEnabled) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Conexion a internet Nulla");

                builder.setMessage("Por favor habilite wifi para conexion a internet.");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialogInterface, int i) {
                                // Show location settings when the user
                                // acknowledges the alert dialog
                                Intent intent = new Intent(
                                        Settings.ACTION_SETTINGS);
                                startActivity(intent);
                            }
                        });
                Dialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            } else {
                try {
                    Thread_log();
                } catch (Exception e) {
                    Log.e("Error login", "" + e);
                }
            }
        }


    //Metodo para saber el estado del servidor
    public boolean StatusLine(){
        try {
            //Instanciamos un objeto de tipo URL pasandole la ruta del servidor
            URL url = new URL(URL_connect);
            //Instanciamos un objeto tipo HttpURLConnection para abrir una conexi�n
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(10 * 1000);          // Esperamos 10 s.
            //Conectamos al servidor
            urlc.connect();

            if (urlc.getResponseCode() == 200) {        // Si la respuesta es igual a 200 hay conexi�n.
                Log.wtf("Connection", "Success !");
                return true;
            } else {
                return false;
            }


        } catch (MalformedURLException e1) {// Por cualquier Cosa! xD
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}