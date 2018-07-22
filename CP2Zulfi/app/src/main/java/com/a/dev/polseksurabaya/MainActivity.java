package com.a.dev.polseksurabaya;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    GPSTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsTracker = new GPSTracker(MainActivity.this);
        //activated Maps
        EnebleGPSIfPosible();

        //setTitle Toolbar
        //getSupportActionBar().setDisplayShowTitleEnabled(false);//menghapus title bar
        getSupportActionBar().setTitle("Halaman Utama");

        //initializing the tab layout
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //adding the tab using asstab method

        tabLayout.addTab(tabLayout.newTab().setText("Utama"));//set tab title
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //initial view pagger
        viewPager=(ViewPager)findViewById(R.id.pager);

        //creating out pagger adapter
        Pager adapter = new Pager(getSupportFragmentManager(),tabLayout.getTabCount());

        //adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //adding onTabSelecListener to swipe Views
        tabLayout.setOnTabSelectedListener(this);
    }



    private void EnebleGPSIfPosible() {
        final LocationManager manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.setting_dialog);
        dialog.setCancelable(false);

        Button setting    = (Button) dialog.findViewById(R.id.btsetting);
        TextView warning    = (TextView) dialog.findViewById(R.id.tvwarning);
        dialog.show();

        warning.setText("Kamu Harus Mengaktifkan GPS untuk menggunakan Aplikasi ini. Nyalakan Sekarang pada Pengaturan?");
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        //viewPager.setCurrentItem(tab.getPosition());
        switch (tab.getPosition()){
            case 0:
                viewPager.setCurrentItem(0);
                getSupportActionBar().setTitle("Halaman Utama");
                break;
            case 1:
                viewPager.setCurrentItem(2);
                getSupportActionBar().setTitle("Halaman About");
                break;
            default:
                getSupportActionBar().setTitle("Halaman Utama");
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_lokasi:

                try {
                    final Dialog dialog = new Dialog(MainActivity.this);
                    String lat = String.valueOf(gpsTracker.getLatitude());
                    String longi = String.valueOf(gpsTracker.getLongitude());
                    Geocoder geocoder;
                    List<Address> addresses = null;
                    geocoder = new Geocoder(this, Locale.getDefault());
                    addresses = geocoder.getFromLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    //String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    //String postalCode = addresses.get(0).getPostalCode();
                    //String knownName = addresses.get(0).getFeatureName();


                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.lokasi_dialog);
                    dialog.setCancelable(false);
                    Button oke    = (Button) dialog.findViewById(R.id.btoke);
                    TextView mylocation =(TextView)dialog.findViewById(R.id.tvmylocation);

                    mylocation.setText("Saya Berada Di : "+address+", "+city+", "+country+
                            "\nlatitude= "+lat+"\nlongitude= "+longi);

                    dialog.show();
                    oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                    /*new AlertDialog.Builder(this).setIcon(R.drawable.ic_location).setTitle("Lokasi Saya")
                            .setMessage("\nSaya Berada Di : "+address+", "+city+", "+state+", "+country+
                                    "\n\nlatitude= "+lat+"\nlongitude= "+longi)
                            .setCancelable(true).setPositiveButton("Oke",null).show();*/
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Lokasi Tidak di Temukan, Koneksi Internet Tidak Stabil", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exit_dialog);
        dialog.setCancelable(true);

        Button exit    = (Button) dialog.findViewById(R.id.btoke);
        Button batal    = (Button) dialog.findViewById(R.id.btbatal);
        dialog.show();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
                finish();
                dialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
