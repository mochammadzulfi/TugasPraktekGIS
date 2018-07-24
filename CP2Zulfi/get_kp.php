<?php  
 include "conf.php"; // konek database
 $hasil = mysql_query("SELECT * FROM data_kantorpolisi") or die(mysql_error()); // query sql
 $response = array();
 
//jika data nya ada atau lebih dari 0
// if(mysql_num_rows($hasil)> 0){

//     $response['result']= "true" ;
//     $response['msg']="Data ditemukan";
//     $response["data"] = array();

    // fungsi perulangan

 
 while ($row = mysql_fetch_array($hasil)) {
 	$response[]=$row;

 }

  echo json_encode($response);
 
// } else {  
//  $response['result']= "false" ;
//     $response['msg']="maaf,terjadi kesalahan";
// }

?>