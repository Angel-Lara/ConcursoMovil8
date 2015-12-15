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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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


public class Fragment1 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private static final String TAG = Fragment1.class.getSimpleName();

    public static TextView fecha_text;
    public static TextView usFrag1, hora;
    public static Button envio;
    public  static  EditText obs;
    static ProgressDialog pDialog;
    TimePick h = new TimePick();
    String URL_connect = "http://192.168.10.103/Farol_rojo/restaurant.php";
    Handler handler = new Handler();


    public static String ubicacion, fecha_est, hora_est, total_pers, observaciones_user;
    Spinner cat, mesa_user;


    public Fragment1() {
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
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        usFrag1 = (TextView) v.findViewById(R.id.User);
        obs = (EditText)v.findViewById(R.id.descripcion_input);
        envio = (Button) v.findViewById(R.id.aceptar_reservacion);
        fecha_text = (TextView) v.findViewById(R.id.fecha_ejemplo_text);
        hora = (TextView) v.findViewById(R.id.Hora_example);
        cat = (Spinner) v.findViewById(R.id.categoria_spinner);
        mesa_user = (Spinner) v.findViewById(R.id.mesas);


        envio.setOnClickListener(this);

        usFrag1.setText(MainActivity.user.toString());
        cat.setOnItemSelectedListener(this);
        mesa_user.setOnItemSelectedListener(this);

        fecha_text.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actualizarFecha();
                    }
                }
        );

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
        date.show(getFragmentManager(), "Date Picker");
        ;

        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

            }
        };
    }


    public void actualizarHora() {
        // Setear en el textview la fecha
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("hora", calender.get(Calendar.HOUR));
        args.putInt("min", calender.get(Calendar.MINUTE));
        args.putInt("AM_PM", calender.get(Calendar.AM_PM));

        h.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        h.setCallBack(ond);
        h.show(getFragmentManager(), "Time Picker");
        ;


        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

            }
        };
    }


    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Log.e("co", String.valueOf(year));
            fecha_text.setText(String.valueOf(year) + "/" + String.valueOf(++monthOfYear) + "/" + String.valueOf(dayOfMonth));
            fecha_est = String.valueOf(year) + "/" + String.valueOf(++monthOfYear) + "/" + String.valueOf(dayOfMonth);
        }
    };

    TimePickerDialog.OnTimeSetListener ond = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
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
                if(fecha_est != null && fecha_est != "" &&hora_est!= ""&&hora_est!=null){
                    Thread_log();
                    observaciones_user =obs.getText().toString();
                }else{
                    Toast.makeText(getActivity(), "Establesca Fecha y Hora Correcta",Toast.LENGTH_SHORT).show();
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
                    ifo.add(new BasicNameValuePair("cat", ubicacion));
                    ifo.add(new BasicNameValuePair("fecha", fecha_est));
                    ifo.add(new BasicNameValuePair("hora", hora_est));
                    ifo.add(new BasicNameValuePair("total", total_pers));
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
                                Toast.makeText(getActivity(), "Los datos no se pudieron almacenar /n" + "Intentelo mas Tarde", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        pDialog.dismiss();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Almacenamiento Exitoso!!!!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.categoria_spinner:
                ubicacion = cat.getSelectedItem().toString();
                Log.e("categoria",ubicacion);
                break;
            case R.id.mesas:
                total_pers= mesa_user.getSelectedItem().toString();
                Log.e("mesas",total_pers);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


