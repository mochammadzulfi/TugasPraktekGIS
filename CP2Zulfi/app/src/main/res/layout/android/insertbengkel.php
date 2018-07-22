<?php
if($_SERVER['REQUEST_METHOD'] == 'POST')
 {

    date_default_timezone_set('Asia/Jakarta');//setting timezone to indonesia/jakarta

include "conf.php"; // konek database

$DefaultName = date("YmdHis");
 
    $ImageData = $_POST['image'];
 
    $ImagePath = "fotobengkel/$DefaultName.png";
 
    $ServerURL = "http://192.168.43.186/eserver/android/$ImagePath";

 //Getting post data 
 $nama_bengkel = $_POST['nama_bengkel'];
 $alamat_bengkel = $_POST['alamat_bengkel'];
 $telepone = $_POST['telepone'];
 $fasilitas = $_POST['fasilitas'];
 $keterangan = $_POST['keterangan'];
 $latitude = $_POST['latitude'];
 $longitude = $_POST['longitude'];

$Sql_Query = "INSERT INTO data_bengkel (id_bengkel,nama_bengkel, alamat_bengkel, kecamatan, latitude, longitude, telepon, fasilitas, keterangan, gambar) values(NULL,'$nama_bengkel','$alamat_bengkel','','$latitude','$longitude','$telepone','$fasilitas','$keterangan','$ServerURL')"or die("connection failed");
$result = mysql_query($Sql_Query) or die(mysql_error());
        
    if($result==true) { //check if query successful
        echo "Terima Kasih Data Bengkel Berhasil Diinputkan";
          }
          else
          {
            echo "Data Bengkel Gagal Diinputkan";
          }

         if(file_put_contents($ImagePath,base64_decode($ImageData))!= false){
                echo "upload_succes";
                }
                 else{
                    echo "Not Uploaded";
                    }
}
?>