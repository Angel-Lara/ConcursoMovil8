package mx.edu.itchetumal.concursomovil8;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PC-04 on 01/12/2015.
 */
public class HomePage extends Fragment {
private FloatingActionButton actualizar;
    private static String usuario;
    static Context context;
    TextView textUsuario;
    busquedas busqueda;
    static ListView lisRes,listTa,listgym,listAlar,salidalist;
    ArrayList<HashMap<String, String>> adaptador;
    SimpleAdapter a;
    String URL_connect = "http://192.168.10.103/Farol_rojo/";
    String bus_uno = "info_Servicio_Restaurant.php";
    String bus_dos = "info_Servicio_Taxi.php";
    String bus_tres = "info_Servicio_Gym.php";
    String bus_cuatro = "info_Servicio_Despertador.php";
    String bus_cinco = "info_Servicio_Salida.php";
    static HttpEntity entity;
    static ArrayList<HashMap<String, String>> reslist = new ArrayList<HashMap<String, String>>();
    static ArrayList<HashMap<String, String>> taxlist = new ArrayList<HashMap<String, String>>();
    static ArrayList<HashMap<String, String>> gymlist = new ArrayList<HashMap<String, String>>();
    static ArrayList<HashMap<String, String>> alarmalist = new ArrayList<HashMap<String, String>>();
    static ArrayList<HashMap<String, String>> sallist = new ArrayList<HashMap<String, String>>();
    JSONObject jsonobject;
    String[] from = new String[] {"Hora", "Fecha", "Observaciones"};
    int[] to = new int[] { R.id.Hora, R.id.Fecha, R.id.observaciones};
    restaurant n1=new restaurant();
    taxi n2=new taxi();
   gym n3=new gym();
    alarma n4=new alarma();
  salida n5=new salida();
    public HomePage(){}
   static View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home, container, false);
        context= getActivity();
        busqueda =new busquedas();

        n1.execute();
        n2.execute();
        n3.execute();
        n4.execute();
        n5.execute();
        lisRes = (ListView)rootView.findViewById(R.id.listRes);
        listTa =(ListView)rootView.findViewById(R.id.listTax);
        listgym=(ListView)rootView.findViewById(R.id.gym);
        listAlar=(ListView)rootView.findViewById(R.id.alarma);
        salidalist =(ListView)rootView.findViewById(R.id.salida);
        textUsuario = (TextView) rootView.findViewById(R.id.User);
        textUsuario.setText(MainActivity.user.toString());





        SimpleAdapter adapter1 = new SimpleAdapter(getActivity(), reslist, R.layout.columns_3, from, to);
        lisRes.setAdapter(adapter1);
        SimpleAdapter adapter2 = new SimpleAdapter(getActivity(), taxlist, R.layout.columns_3, from, to);
        listTa.setAdapter(adapter2);
        SimpleAdapter adapter3 = new SimpleAdapter(getActivity(), gymlist, R.layout.columns_3, from, to);
        listgym.setAdapter(adapter3);
        SimpleAdapter adapter4 = new SimpleAdapter(getActivity(), alarmalist, R.layout.columns_3, from, to);
        listAlar.setAdapter(adapter4);
        SimpleAdapter adapter5= new SimpleAdapter(getActivity(), sallist, R.layout.columns_3, from, to);
        salidalist.setAdapter(adapter5);
        reslist.clear();
        taxlist.clear();
        gymlist.clear();
        alarmalist.clear();
        sallist.clear();
        return rootView;
    }







    class restaurant extends AsyncTask<Void, Void,Void > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            busqueda.progresBar();

        }
        @Override
        protected Void doInBackground(Void... v) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL_connect + bus_uno);
                List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                httppost.setEntity(new UrlEncodedFormEntity(ifo));
                HttpResponse responses = httpclient.execute(httppost);
                entity = responses.getEntity();
                String a = EntityUtils.toString(entity);


                JSONArray jsonArray = new JSONArray(a);
                HashMap<String, String> map ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobject = jsonArray.getJSONObject(i);
                    map = new HashMap<String,String>();

                    map.put("Hora", (String)jsonobject.getString("hora"));
                    map.put("Fecha",(String) jsonobject.getString("fecha"));
                    map.put("Observaciones", (String)jsonobject.getString("observaciones"));
                    reslist.add(map);

                }
                Log.e("datp",reslist.toString());

            }catch (JSONException e) {
                Log.e("Error dato1", e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
           // busqueda.cerrar();
        }

    }
    class taxi extends AsyncTask<Void, Void,Void > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // busqueda.progresBar();
        }
        @Override
        protected Void doInBackground(Void... v) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL_connect + bus_dos);
                List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                httppost.setEntity(new UrlEncodedFormEntity(ifo));
                HttpResponse responses = httpclient.execute(httppost);
                entity = responses.getEntity();
                String a = EntityUtils.toString(entity);


                JSONArray jsonArray = new JSONArray(a);
                HashMap<String, String> map ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobject = jsonArray.getJSONObject(i);
                    map = new HashMap<String,String>();

                    map.put("Hora", (String)jsonobject.getString("hora"));
                    map.put("Fecha",(String) jsonobject.getString("fecha"));
                    map.put("Observaciones", (String)jsonobject.getString("observaciones"));
                    taxlist.add(map);

                }
                Log.e("datp",taxlist.toString());

            }catch (JSONException e) {
                Log.e("Error dato1", e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            busqueda.cerrar();
        }

    }
    class gym extends AsyncTask<Void, Void,Void > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // busqueda.progresBar();
        }
        @Override
        protected Void doInBackground(Void... v) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL_connect + bus_tres);
                List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                httppost.setEntity(new UrlEncodedFormEntity(ifo));
                HttpResponse responses = httpclient.execute(httppost);
                entity = responses.getEntity();
                String a = EntityUtils.toString(entity);


                JSONArray jsonArray = new JSONArray(a);
                HashMap<String, String> map ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobject = jsonArray.getJSONObject(i);
                    map = new HashMap<String,String>();

                    map.put("Hora", (String)jsonobject.getString("hora"));
                    map.put("Fecha",(String) jsonobject.getString("fecha"));
                    map.put("Observaciones", (String)jsonobject.getString("observaciones"));
                    gymlist.add(map);

                }
                Log.e("datp",gymlist.toString());

            }catch (JSONException e) {
                Log.e("Error dato1", e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            busqueda.cerrar();
        }

    }
    class alarma extends AsyncTask<Void, Void,Void > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // busqueda.progresBar();
        }
        @Override
        protected Void doInBackground(Void... v) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL_connect + bus_cuatro);
                List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                httppost.setEntity(new UrlEncodedFormEntity(ifo));
                HttpResponse responses = httpclient.execute(httppost);
                entity = responses.getEntity();
                String a = EntityUtils.toString(entity);


                JSONArray jsonArray = new JSONArray(a);
                HashMap<String, String> map ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobject = jsonArray.getJSONObject(i);
                    map = new HashMap<String,String>();

                    map.put("Hora", (String)jsonobject.getString("hora"));
                    map.put("Fecha",(String) jsonobject.getString("fecha"));
                    map.put("Observaciones", (String)jsonobject.getString("observaciones"));
                    alarmalist.add(map);

                }
                Log.e("datp",alarmalist.toString());

            }catch (JSONException e) {
                Log.e("Error dato1", e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            busqueda.cerrar();
        }

    }
    class salida extends AsyncTask<Void, Void,Void > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // busqueda.progresBar();
        }
        @Override
        protected Void doInBackground(Void... v) {
            // TODO Auto-generated method stub
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(URL_connect + bus_cinco);
                List<NameValuePair> ifo = new ArrayList<NameValuePair>();
                ifo.add(new BasicNameValuePair("us", MainActivity.user.toString()));
                httppost.setEntity(new UrlEncodedFormEntity(ifo));
                HttpResponse responses = httpclient.execute(httppost);
                entity = responses.getEntity();
                String a = EntityUtils.toString(entity);


                JSONArray jsonArray = new JSONArray(a);
                HashMap<String, String> map ;

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonobject = jsonArray.getJSONObject(i);
                    map = new HashMap<String,String>();

                    map.put("Hora", (String)jsonobject.getString("hora"));
                    map.put("Fecha",(String) jsonobject.getString("fecha"));
                    map.put("Observaciones", (String)jsonobject.getString("observaciones"));
                    sallist.add(map);

                }
                Log.e("datp",sallist.toString());

            }catch (JSONException e) {
                Log.e("Error dato1", e.toString());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            busqueda.cerrar();
        }

    }

}