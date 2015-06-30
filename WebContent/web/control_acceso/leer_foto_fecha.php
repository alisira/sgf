<?php
header('Content-Type: text/html; charset=ISO-8859-1');
$dia= $_GET['dia'];
//$hora= $_GET['hora'];
$num= $_GET['num'];

$hora_ini= $_GET['hora_ini'];
$hora_fin= $_GET['hora_fin'];


//$foto = buscar_foto($dia, $num, $hora);

$foto = buscar_foto_hora($dia, $hora_ini, $hora_fin, $num);

echo $foto;

function buscar_foto_hora($dia, $hora_ini, $hora_fin, $num){
    //$a = "video/video_".$dia.".log";
    //$fp = fopen($a);
    $fp = fopen("video/video_" . $dia . ".log", "r");
    
    $con = 0;
    while ($linea = fgets($fp)){

        $row = explode('|', $linea);
        $hora = strtotime($row[0]);

        if ($hora && $hora >= strtotime($hora_ini) && $hora <= strtotime($hora_fin)){
            $con++;
            if ($num == $con) {
                //return $row[1];
                return $linea;
            }
        }
    }
    return false;
}


function buscar_foto($dia, $num, $hora) {
    //$a = "video/video_".$dia.".log";
    //$fp = fopen($a);
    $fp = fopen("video/video_" . $dia . ".log", "r");

    $con = 0;
    while ($linea = fgets($fp)) {

        $row = explode('|', $linea);

        if ($row[0] == $hora) {
            $con++;
            if ($num == $con) {
                return $row[1];
            }
        }
    }
    return false;
}

//error_log($foto, 0);

?>
