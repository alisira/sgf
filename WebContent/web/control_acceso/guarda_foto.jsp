<?php
header('Content-Type: text/html; charset=ISO-8859-1');
$foto = $_GET['foto'];
//$foto2= $_GET['foto2'];
//echo $foto;
//echo "Prueba";

//error_log($foto, 0);
//error_log($foto2, 0);

$arch = "video/video_".date("dmY").".log"; 
$archivo = fopen($arch,"a+");

$now = new DateTime;
//echo $now->getTimestamp();
//echo $now->format('H:i:s');
$texto_guardar = $now->format('H:i:s').'|'.$foto;

fwrite($archivo,$texto_guardar."\n");
fclose($archivo);

?>
