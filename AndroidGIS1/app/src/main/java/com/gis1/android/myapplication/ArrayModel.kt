package com.gis1.android.myapplication

class ArrayModel {
    var rowid : Int = 0
    var lokasi : String = ""
    var long : String = ""
    var lat : String = ""

    constructor(rowid: Int, lokasi: String, long: String, lat: String){
        this.rowid = rowid
        this.lokasi = lokasi
        this.long = long
        this.lat = lat
    }

    constructor(lokasi: String, long: String, lat: String){
        this.lokasi = lokasi
        this.long = long
        this.lat = lat
    }
}
