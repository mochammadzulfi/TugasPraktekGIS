package com.a.dev.polseksurabaya;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GPSTracker gpsTracker;
    String objLat, objLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        gpsTracker = new GPSTracker(MapsActivity.this);
        objLat = getIntent().getStringExtra("latitude");
        objLong = getIntent().getStringExtra("longitude");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String lat = String.valueOf(gpsTracker.getLatitude());
        String longi = String.valueOf(gpsTracker.getLongitude());
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        // Add a marker in myLocation and move the camera
        try {
            List<Address>  addresses = geocoder.getFromLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

            LatLng lokasiku = new LatLng(Double.parseDouble(lat),Double.parseDouble(longi));

            mMap.addMarker(new MarkerOptions().position(lokasiku).title(address+", "+city+", "+state+", "+country)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pointuser));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lokasiku));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasiku,10));


            String latobj = String.valueOf(objLat);
            String longiobj = String.valueOf(objLong);
            Geocoder geocoderobj = new Geocoder(this, Locale.getDefault());
            List<Address> addressesobj = geocoderobj.getFromLocation(Double.parseDouble(latobj), Double.parseDouble(longiobj), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String addressobj = addressesobj.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String cityobj = addressesobj.get(0).getLocality();
            String stateobj = addressesobj.get(0).getAdminArea();
            String countryobj = addressesobj.get(0).getCountryName();
            String postalCodeobj = addressesobj.get(0).getPostalCode();
            String knownNameobj = addressesobj.get(0).getFeatureName();

            LatLng lokobj = new LatLng(Double.parseDouble(latobj),Double.parseDouble(longiobj));

            mMap.addMarker(new MarkerOptions().position(lokobj).title(addressobj+", "+cityobj+", "+stateobj+", "+countryobj)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pointobj));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(lokobj));


            //Execution location
            String url = getUrl(lokasiku, lokobj);

            new FetchUrl().execute(url);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(lokasiku));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        } catch (IOException e) {
            e.printStackTrace();
            View view = findViewById(android.R.id.content);
            Snackbar.make(view, "Jaringan Internet Tidak Stabil, Silahkan Coba Lagi...", Snackbar.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(), "Koneksi Internet Tidak Stabil, Coba Lagi", Toast.LENGTH_SHORT).show();
        }
    }
    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
//                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
//                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
//            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
//            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
//                Log.d("ParserTask",jsonData[0].toString());
                MapDirection parser = new MapDirection();
//                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
//                Log.d("ParserTask","Executing routes");
//                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
//                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);

//                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
//                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
}
