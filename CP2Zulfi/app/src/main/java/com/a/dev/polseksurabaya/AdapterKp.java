package com.a.dev.polseksurabaya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterKp extends BaseAdapter {
    Context context;
    ArrayList<HashMap<String, String>>arrayList;
    GPSTracker gpsTracker;

    public AdapterKp(Context context, ArrayList<HashMap<String, String>>arrayList){
        this.context= context;
        this.arrayList=arrayList;
        this.gpsTracker = new GPSTracker(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //posisi Saat ini dari georecoder
//        String lat = String.valueOf(gpsTracker.getLatitude());
//        String longi = String.valueOf(gpsTracker.getLongitude());

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.row_kp, viewGroup, false);

        ImageView gbkp=(ImageView) view.findViewById(R.id.gbkp);

        TextView namakp=(TextView)view.findViewById(R.id.nmkp);
        TextView alamat=(TextView)view.findViewById(R.id.alamatkp);
        TextView jarak=(TextView)view.findViewById(R.id.jarak);
        TextView telepon=(TextView)view.findViewById(R.id.txt_telepon);

//        TextView latlok=(TextView)view.findViewById(R.id.latlok);
//        TextView longlok=(TextView)view.findViewById(R.id.longlok);
//
//        TextView latku=(TextView)view.findViewById(R.id.latku);
//        TextView longku=(TextView)view.findViewById(R.id.longku);

        namakp.setText(arrayList.get(i).get("NamaKp"));
        alamat.setText(arrayList.get(i).get("Alamat"));
        telepon.setText(arrayList.get(i).get("Telepon"));
        jarak.setText(arrayList.get(i).get("jarak")+" Km");
//        latlok.setText(arrayList.get(i).get("Latlok"));
//        longlok.setText(arrayList.get(i).get("Longlok"));

//        latku.setText(lat);
//        longku.setText(longi);

        return view;
    }
}
