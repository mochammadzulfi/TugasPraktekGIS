<?php
$host = "localhost";
$user = "root";
$pass = "";
$namadb = "emergency";

mysql_connect($host,$user, $pass);
mysql_select_db($namadb);

error_reporting(E_ALL ^ ~E_NOTICE ^ ~E_DEPRECATED);
?>