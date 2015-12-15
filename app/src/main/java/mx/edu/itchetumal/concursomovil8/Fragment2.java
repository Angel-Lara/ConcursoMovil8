package mx.edu.itchetumal.concursomovil8;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment2 extends Fragment implements View.OnClickListener {
    private static final String TAG = Fragment1.class.getSimpleName();
    public static  TextView fe;
    public static TextView  usFrag1, hora;
    public static String ubicacion, fecha_est, hora_est, total_pers, observaciones_user,or_est,des_t;
    public static EditText origen_est,destino_est,obs;;
    public static Button envio;
    static ProgressDialog pDialog;
    MainActivity nu = new MainActivity();
    String ex ="";
    int hr,m,day,mon,year=0;   TimePick h = new TimePick();
    DatePickerFragment da = new DatePickerFragment();
    String URL_connect = "http://192.168.10.103/Farol_rojo/reservacionTaxi.php";
    Handler handler = new Handler();




    public Fragment2() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar al fragmento para contribuir en la action bar
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment2, container, false);
        usFrag1 = (TextView)v.findViewById(R.id.user);
        envio = (Button) v.findViewById(R.id.aceptar_reservacion);
        obs = (EditText)v.findViewById(R.id.descripcion_input);
        origen_est = (EditText)v.findViewById(R.id.origen);
        destino_est=(EditText)v.findViewById(R.id.destino);
        usFrag1.setText(MainActivity.user.toString());
        fe = (TextView) v.findViewById(R.id.fecha_t);
        envio.setOnClickListener(this);
        fe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                          actualizarFecha();
                    }
                }
        );
        hora = (TextView)v.findViewById(R.id.hora2);
        hora.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarHora();
                    }
                }

        );
        return v;


    }
    public void actualizarFecha() {
        // Setear en el textview la fecha
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");;

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

        }
    };}


    public void actualizarHora() {
        // Setear en el textview la fecha
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("hora", calender.get(Calendar.HOUR));
        args.putInt("min", calender.get(Calendar.MINUTE));

        h.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        h.setCallBack(ond);
        h.show(getFragmentManager(), "Time Picker");;


        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

            }
        };}




    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Log.e("co",String.valueOf(year));
            fe.setText(String.valueOf(year) + "/" + String.valueOf(++monthOfYear) + "/" + String.valueOf(dayOfMonth));
            fecha_est = String.valueOf(year) + "/" + String.valueOf(++monthOfYear) + "/" + String.valueOf(dayOfMonth);
        }
    };

    TimePickerDialog.OnTimeSetListener ond = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.e("co",String.valueOf(hourOfDay));
            hora.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));

            Log.e("co", String.valueOf(hourOfDay));
            String minu="";
            if(minute >=10){
                minu = String.valueOf(minute);
            }else{
                minu = "0"+String.valueOf(minute);
            }
            if (hourOfDay > 12 ) {
                hora.setText(String.valueOf(hourOfDay) + ":" + minu + "pm");
                hora_est = String.valueOf(hourOfDay) + ":" + minu + "pm";
            }
            if (hourOfDay == 12) {
                hora.setText(String.valueOf(hourOfDay) + ":" + minu + "pm");
                hora_est = String.valueOf(hourOfDay) + ":" + minu + "pm";
            }
            if (hourOfDay < 12 && hourOfDay<= 10) {
                if (hourOfDay != 0) {
                    hora.setText("0"+String.valueOf(hourOfDay) + ":" + minu + "am");
                    hora_est = "0"+String.valueOf(hourOfDay) + ":" + minu + "am";
                } else {
                    hora.setText("12" + ":" + minu + "am");
                    hora_est = String.valueOf(hourOfDay) + ":" + String.valueOf(minute) + "pm";

                }
            }



        }
};

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aceptar_reservacion:
                verificarConnecion();
                break;
        }
    }

    public void verificarConnecion() {
        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        boolean wifiEnabled = wifiManager.isWifiEnabled();
        if (!wifiEnabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                or_est = origen_est.getText().toString();
                des_t = destino_est.getText().toString();
                if((fecha_est != null && fecha_est != "" &&hora_est!= ""&&hora_est!=null) &&
                        (or_est != "" && or_est!=null && des_t != "" && des_t != "")) {

                        Thread_log();
                        observaciones_user = obs.getText().toString();
                        or_est = origen_est.getText().toString();
                        des_t = destino_est.getText().toString();

                }else{
                    Toast.makeText(getActivity(), "Establesca bien los datos...",Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.e("Error login", "" + e);
            }
        }
    }

    public void Thread_log() {
        //para el progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Guardando datos en la nube..../n" + "Guardando");
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
                    List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                    ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                   ifo.add(new BasicNameValuePair("origen", or_est));
                    ifo.add(new BasicNameValuePair("destino", des_t));
                    ifo.add(new BasicNameValuePair("fecha", fecha_est));
                    ifo.add(new BasicNameValuePair("hora", hora_est));
                    ifo.add(new BasicNameValuePair("des", observaciones_user));


                    httppost.setEntity(new UrlEncodedFormEntity(ifo));
                    HttpResponse responses = httpclient.execute(httppost);
                    HttpEntity entity = responses.getEntity();
                    String text = EntityUtils.toString(entity);
                    Log.e("Mensajito:", " " + text);

                    if (text.equalsIgnoreCase("false")) {
                        pDialog.dismiss();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Los datos no se pudieron almacenar /n" + "Intentelo mas Tarde", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        pDialog.dismiss();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Almacenamiento Exitoso!!!!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }


}
