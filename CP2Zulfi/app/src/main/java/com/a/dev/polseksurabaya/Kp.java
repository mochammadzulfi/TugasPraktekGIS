package com.a.dev.polseksurabaya;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Kp extends AppCompatActivity {
    ListView list;
    ArrayList<HashMap<String, String>>arrayList;
    AdapterKp adapterKp;
    GPSTracker gpsTracker;
    TextView warning;
    Button btnRefresh;
    ImageButton  noinet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kp);
        gpsTracker = new GPSTracker(Kp.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set gradient colour on toolbar
        //getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgtoolbar));
        //set title bar
        getSupportActionBar().setTitle("Daftar Data Kantor Polisi");
        //daftarkan txtview dan button pada layout actifity_Kp untuk melakukan pengecekan

        warning = (TextView)findViewById(R.id.tvwarning);
        btnRefresh = (Button)findViewById(R.id.btrefresh);
        noinet = (ImageButton)findViewById(R.id.btnointernet);

        //daftarkan list untuk menampilkan data secara Array
        list=(ListView)findViewById(R.id.list_kp);
        arrayList=new ArrayList<>();

        refresh();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
    }

    private void refresh(){
        if(isConnectingToInternet())   { //jika ad internet
            warning.setVisibility(View.GONE);
            btnRefresh.setVisibility(View.GONE);
            noinet.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            new sync().execute();
        }else  {                                //jika tidak
            warning.setVisibility(View.VISIBLE);
            noinet.setVisibility(View.VISIBLE);
            btnRefresh.setVisibility(View.GONE);
            list.setVisibility(View.GONE);
            warning.setText("Tidak ada Koneksi internet!!!");
        }
    }

    private boolean isConnectingToInternet(){ //checking internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            //Toast.makeText(getApplicationContext(), "no internet", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    class sync extends AsyncTask<Void, String, String>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(Kp.this);
            progressDialog.setMessage("Loading!!!");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result="";
            try {
                HttpParams myHttpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(myHttpParams,15000);
                HttpConnectionParams.setSoTimeout(myHttpParams, 15000);
                DefaultHttpClient httpClient=new DefaultHttpClient(myHttpParams);
                HttpGet myHttpGet=new HttpGet("https://mzulfi.000webhostapp.com/get_kp.php");
                HttpResponse httpResponse=httpClient.execute(myHttpGet);
                HttpEntity httpEntity=httpResponse.getEntity();
                result= EntityUtils.toString(httpEntity);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            //Log.d("datalog", s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object= jsonArray.getJSONObject(i);

                    //variabel lokasi posisi object
                    Double latpos = Double.parseDouble(object.getString("latitude"));
                    Double longpos = Double.parseDouble(object.getString("longitude"));

                    final Location lokCek = new Location("Titik Lokasi");
                    lokCek.setLatitude(latpos);
                    lokCek.setLongitude(longpos);

                    String lat = String.valueOf(gpsTracker.getLatitude());
                    String longi = String.valueOf(gpsTracker.getLongitude());

                    //posisi Saat ini dari georecoder
                    Double latme = Double.parseDouble(lat);
                    Double longme = Double.parseDouble(longi);

                    final Location newLoc = new Location("Titik GPS");
                    newLoc.setLatitude(latme);
                    newLoc.setLongitude(longme);

                    //6371 = Radius bumi dalam satuan kilometer
                    final double distance = 6371 * Math.acos( Math.cos( Math.toRadians(newLoc.getLatitude()) )
                            * Math.cos( Math.toRadians( lokCek.getLatitude() ) )
                            * Math.cos( Math.toRadians( lokCek.getLongitude() )
                            - Math.toRadians(newLoc.getLongitude()) )
                            + Math.sin( Math.toRadians(newLoc.getLatitude()) )
                            * Math.sin( Math.toRadians( lokCek.getLatitude() ) ) ) ;

                    /*DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
                    otherSymbols.setDecimalSeparator(',');
                    otherSymbols.setGroupingSeparator('.');
                    DecimalFormat df = new DecimalFormat("#.###");*/
                    DecimalFormat df = new DecimalFormat("#.###"); //("#.###", otherSymbols);

                    HashMap<String, String> hashmap=new HashMap<>();

                    hashmap.put("NamaKp", object.getString("nama_kantorpolisi"));//kiri nama variabel string dari adapter, kanan nama fild pada database object
                    hashmap.put("Alamat", object.getString("alamat_kantorpolisi"));
                    hashmap.put("Telepon", object.getString("telepon"));
                    hashmap.put("Keterangan", object.getString("keterangan"));
                    hashmap.put("Latlok", object.getString("latitude"));
                    hashmap.put("Longlok", object.getString("longitude"));
                    hashmap.put("jarak", String.valueOf(df.format(distance)).replace(",","."));

                    arrayList.add(hashmap);
                }
                //sorting berdasarkan jarak jarak
                Collections.sort(arrayList, new Comparator<HashMap<String, String>>() {
                    @Override
                    public int compare(HashMap<String, String> stringStringHashMap, HashMap<String, String> t1) {
                        Double i1 = Double.parseDouble(stringStringHashMap.get("jarak"));
                        Double i2 = Double.parseDouble(t1.get("jarak"));
                        return (int) (i1-i2);
                    }
                });

                adapterKp=new AdapterKp(getApplicationContext(),arrayList);
                list.setAdapter(adapterKp);
//               Log.d("dataLog", "isi="+arrayList.size()); //checking isi dalam log D
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        detail(arrayList.get(i));//paggil fungsi detail Kp
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                warning.setVisibility(View.VISIBLE);
                btnRefresh.setVisibility(View.VISIBLE);
                noinet.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    void detail(final HashMap<String, String> item){ //fungsi detail yang memanggil layout detail
        final Dialog dialog = new Dialog(Kp.this);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.detail_kp);
        dialog.setCancelable(true);

        //Toast.makeText(Kp.this,item.get("NamaKp"), Toast.LENGTH_LONG).show();

        TextView tvnamakp   = (TextView) dialog.findViewById(R.id.txt_namakp);
        TextView tvalamatkp   = (TextView) dialog.findViewById(R.id.txt_alamatkp);
        TextView tvketerangan = (TextView) dialog.findViewById(R.id.txt_ket);
        TextView tvtelepon  = (TextView) dialog.findViewById(R.id.txt_telepon);
        TextView tvjarak  = (TextView) dialog.findViewById(R.id.txt_jarak);
        TextView tvlatlok  = (TextView) dialog.findViewById(R.id.txt_latlok);
        TextView tvlonglok    = (TextView) dialog.findViewById(R.id.txt_longlok);
        Button viewmaps    = (Button) dialog.findViewById(R.id.btviewmaps);

        tvnamakp.setText(item.get("NamaKp"));
        tvalamatkp.setText(item.get("Alamat"));
        tvjarak.setText(item.get("jarak")+" Km");
        tvketerangan.setText(item.get("Keterangan"));
        tvtelepon.setText(item.get("Telepon"));
        tvlatlok.setText(item.get("Latlok"));
        tvlonglok.setText(item.get("Longlok"));

        dialog.show();
        viewmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(Kp.this);
                progressDialog.setMessage("Loading!!!");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Intent i = new Intent(Kp.this, MapsActivity.class);
                i.putExtra("latitude", item.get("Latlok"));
                i.putExtra("longitude", item.get("Longlok"));

                progressDialog.dismiss();
                startActivity(i);
                dialog.dismiss();
            }
        });
    }
}
