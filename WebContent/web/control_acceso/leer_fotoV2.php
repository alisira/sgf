<?php

error_reporting(E_ALL);

$now = new DateTime;

$dia = (isset($_POST['dia']) ? $_POST['dia'] : $_GET['dia']);
$hora_ini = (isset($_POST['hora_ini']) ? $_POST['hora_ini'] : $_GET['hora_ini']);
$hora_fin = (isset($_POST['hora_fin']) ? $_POST['hora_fin'] : $_GET['hora_fin']);
$velocidad = (isset($_POST['velocidad']) ? $_POST['velocidad'] : $_GET['velocidad']);

$contexto = explode('/', $_SERVER['SCRIPT_NAME']);
$contexto = $contexto[1];

//echo $contexto[0];
//echo $contexto[1];

//echo 'epa';


//echo $now->getTimestamp();
//echo $now->format('H:i:s');

?>

<html>   
    
    <head>        
        <script src="js/jquery.js"></script>
    </head>
    
    <body onload="consultar();" >
        
        <div>        
            <form name="form" action="leer_fotoV2.php" method="POST" >
                <img id="foto" src="">
                <label>Dia
                    <input id="dia" name="dia" value="<?php echo $dia;?>">
                </label>
                
                <label>Tiempo Inicial
                    <input id="hora_ini" name="hora_ini" value="<?php echo $hora_ini;?>">
                </label>
                <label>Tiempo Final
                    <input id="hora_fin" name="hora_fin" value="<?php echo $hora_fin;?>">
                </label>

                <label>Tiempo 
                    <input id="tiempo" name="tiempo" value="00:00:00">
                </label>
                <label>Velocidad
                    <input id="velocidad" name="velocidad" value="<?php echo $velocidad;?>">
                </label>
                <input type="submit" name="enviar">
            </form>
        </div>
        
    </body>
    
    
    <script type="text/JavaScript">
        
        var tiempo = 50;
        var ciclos = 0;
        var timerID = 0;
        //timerID = setInterval("consultar()", tiempo);
        var req = false;
        var con = 0;
        var hora_ini = '<?php echo $hora_ini ?>';
        var hora_fin = '<?php echo $hora_fin ?>';
        var tiempo_actual = '';
        var datos = 'valor_defecto';
        
        var foto = document.getElementById("foto");       
        var tiempo = document.getElementById("tiempo");        
        var velocidad = document.getElementById("velocidad");        
        var resp;       

        
        function consultar(){
            
            req = false; 
             
            //$.get("leer_foto_1.php",{archivo:"<?php echo $dia ?>"},procesarEventos);
    
            if (ciclos < 30000 && tiempo_actual != hora_fin ){
                //timerID = setInterval("enviar_foto()", tiempo);
                //alert(hora_ini);
                if (window.XMLHttpRequest){
                    req = new XMLHttpRequest();
 
                    // Si no funciona intenta utiliar el objeto IE/Windows ActiveX
                }else if (window.ActiveXObject){
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
 
                if(req!=null){
                    req.onreadystatechange = procesarEventos;                
                    //url = "leer_foto_1.php?archivo=<?php // echo $archivo ?>";
                    //url = "<?php //echo $_SERVER['REQUEST_SCHEME'] ?>://<?php //echo $_SERVER['SERVER_NAME'] ?><?php //echo $_SERVER['CONTEXT_PREFIX'] ?>leer_foto_1.php?archivo=<?php //echo $dia ?>";
                    con++;
                    //url = "<?php // echo $_SERVER['REQUEST_SCHEME'] ?>://<?php //echo $_SERVER['SERVER_NAME'] ?><?php //echo $_SERVER['CONTEXT_PREFIX'] ?>leer_foto_fecha.php?dia=<?php //echo $dia ?>&num="+con+"&hora_ini=<?php //echo $hora_ini ?>&hora_fin=<?php //echo $hora_fin ?>";
                    url = "http://<?php echo $_SERVER['SERVER_NAME'] ?>/<?php echo $contexto ?>/leer_foto_fecha.php?dia=<?php echo $dia ?>&num="+con+"&hora_ini=<?php echo $hora_ini ?>&hora_fin=<?php echo $hora_fin ?>";
                    
                    //alert(url);
                    req.open("GET",url,true);
                    req.send(null);
                }
                ciclos++;
            }else{
                clearInterval(timerID);
                tiempo.value = resp[0]+ ' Terminado '; 
            }
        }

        function procesarEventos(){
            
            if (req.readyState==4 && req.status==200){

                datos  = req.responseText;
                setTimeout(function(){},5);
                
                if (datos.length > 0){
                    resp = datos.split("|");
                    foto.src = resp[1];
                    tiempo.value = resp[0];
                    
                    //t.value = resp[1];
                    if (tiempo_actual == hora_fin){
                        tiempo.value = resp[0]+ ' Terminado ';                        
                        clearInterval(timerID);
                    }
                    
                    controlLatencia(velocidad.value * 100);
                    consultar();
                    
                }else{
                    tiempo.value = resp[0]+ ' Terminado ';                        
                    clearInterval(timerID);
                }
                tiempo_actual=resp[0];
            }
        }
        
        
        function controlLatencia(latencia){
            b=0;
            for (c=0;c<latencia;c++){
                b++;
                setTimeout(function(){},10);
            }
            
        }
        
        function controlLatencia1(tiempo_reci){            
            tiempo1 = new Date(); 
            tiempo1 = Number (tiempo1.getTime());
            
            con= 0;
            
            //while(tiempo_reci < tiempo && con < 10000){
            while(tiempo_reci < tiempo){
                
                tiempo2 = new Date(); 
                tiempo2 = Number (tiempo2.getTime());                
                setTimeout(function(){},1);
                
                tiempo_reci = tiempo_reci + (tiempo2 - tiempo1);
                t2.value = t2.value + ', '+ tiempo_reci;;

            }
        }
        
        function controlLatencia2(){
            
            con= 0;
            tiempo2 = 0
            
            //while(tiempo2 < tiempo_ini + 0.2 && con < 10000){
            tiempo_ini =  Number(tiempo_ini) + Number(0.2);
            
            //alert(tiempo_ini );
            
            while(tiempo2 < tiempo_ini){
                
                //alert (tiempo2 );
                
                tiempo2 = new Date();                
                tiempo2 =  tiempo2.getSeconds() + '.' +  tiempo2.getMilliseconds() + 0.0;
                setTimeout(function(){},5);
                
                
                
                //tiempo2 = (tiempo2 - tiempo_ini);
                
                //alert(tiempo + ' ' + tiempo_reci + ' ' + t2 + ' , ' + t1);
                
                //alert(tiempo_reci);
                
                //t2.value  = tiempo_reci;
                //t2.value = t2.value + ', '+ tiempo2;;
                //t2.value = t2.value + ', '+ con;
                
                

                //alert(tempo + ' b');
            }
        }
        
        function controlLatencia3(tiempo_reci){            
            //tiempo1 = new Date();                
            //tiempo1 =  tiempo1.getSeconds() + '.' +  tiempo1.getMilliseconds() + 0.0;
            
            tiempo_reci = 0;
            
            con= 0;
            
            while(tiempo_reci < tiempo && con < 10000){
                
                con++;
                
                tiempo2 = new Date();                
                tiempo2 =  tiempo2.getSeconds() + '.' +  tiempo2.getMilliseconds() + 0.0;
                setTimeout(function(){},5);
                
                tiempo_reci = tiempo_reci + (tiempo2 - tiempo_ini);
                
                //alert(tiempo + ' ' + tiempo_reci + ' ' + t2 + ' , ' + t1);
                
                //alert(tiempo_reci);
                
                //t2.value  = tiempo_reci;
                t2.value = t2.value + ', '+ tiempo_reci;;
                //t2.value = t2.value + ', '+ con;

                //alert(tempo + ' b');
            }
        }
        
        
        
        
        
        
    </script>
    
</html>