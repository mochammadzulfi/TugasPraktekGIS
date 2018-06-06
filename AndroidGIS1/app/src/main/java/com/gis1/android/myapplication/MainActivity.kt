package com.gis1.android.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Calendar.getInstance()
        val year:Int =c.get(Calendar.YEAR)
        val month:Int =c.get(Calendar.MONTH)
        val day:Int =c.get(Calendar.DAY_OF_MONTH)
        label.text = "$day/$month/$year"

        fun readData(): MutableList<ArrayModel> {
            val list = ArrayList<ArrayModel>()
            val teks : String = etLokasi.text.toString()
            list.add(ArrayModel(1, "Narotama", "-7.288075", "112.776449"))
            list.add(ArrayModel(2, "Itats", "-7.2910111", "112.7785103"))
            for (n : ArrayModel in list ){
                if (teks == n.lokasi){
                    tvLong.setText(n.long)
                    tvLat.setText(n.lat)
                    Toast.makeText(this, "Data Ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
            return list
        }

        btntampil.setOnClickListener{readData()}

        //memindah activity_main ke map narotama
        btnmapn.setOnClickListener {
            val maintomap = Intent(this, MapsActivity::class.java)
            startActivity(maintomap)
        }
        btnmapi.setOnClickListener {
            val maintomap2 = Intent(this, MapsActivity2::class.java)
            startActivity(maintomap2)
        }
    }

}
