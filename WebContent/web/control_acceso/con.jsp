<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" %>
<%

String visibleFoto = "none";
String visibleVideo = "block";

String tabla="control_acceso";
String tabla_nb="Control de Acceso";
String tabla_id="id_control_acceso";

//String this_script=basename($_SERVER['PHP_SELF']);
//String this_script_query=basename($_SERVER['QUERY_STRING']);

String permisos="158"; // variable encargada de la permisologia
//String hoy=date('Y-m-d');


//error_reporting(E_ALL);

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.ico" />
        <title>"PRUEBA"</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="global_admin.css" rel="stylesheet" type="text/css" />        
        
        <script src="jquery-2.1.1.min.js"></script>
        <script src="main.js"></script>
        
        <style type="text/css">
            .contenedor{ width: 400px; float: left;}
            .titulo{ font-size: 12pt; font-weight: bold;}
            #video, #micanvas{
                width: 335px;
                min-height: 250px;
                border: 1px solid #008000;
            }
        </style>        
    </head>

    <body class="fondo" onload="enviar_foto();" >
        <table width="980" border="0" align="center" cellpadding="0" cellspacing="0" class="fondo_tabla01">

            <tr>
                <td><hr /></td>
            </tr>

            <tr>
                <td>
                    <table width="84%" border="0" align="center" cellpadding="4" cellspacing="0" class="fondo_tabla01" >                        

                        <tr>
                            <td colspan="2" class="titulo01" align="center"><div class="titulo01" align="center" style="font-size:14px">Control de Acceso de Empleados</td>
                        </tr>

                        <tr>
                            <td colspan="2" class="error" align="center"><?= mensaje ?></td>
                        </tr>
                        
                        <tr>
                            <td align="center" valing="middle" colspan="2">
                                
                                <form name="frm" method="post" action="control_acceso_tipo.php"  onsubmit="enviar();">

                                    <div id="contenedorVideo" class="contenedor" style="display:<?php echo $visibleVideo; ?>; caption-side: top; clear: inherit; clip: inherit;float: left;">
                                        <video id="video" autoplay="autoplay" ></video>
                                    </div>
                                    
                                    <div id="contenedorFoto" class="contenedor" style="display:<?php echo $visibleFoto; ?>; caption-side: top; clear: inherit; clip: inherit;float: left;">
                                        <img id="fotoTrabajador" name="fotoTrabajador" src="<?php //echo $_POST['fotoTXT']; ?>"  >                                       
                                    </div>
                                    
                                    <div class="contenedor">
                                        <br><br>
                                        <b><font color="#006600" style="font-size:18px">
                                            CEDULA DEL EMPLEADO
                                            </font>
                                        </b>
                                        <input id="cedu" name="cedu" type="text" value="" onkeypress="ocultar_mostrar();"  > 
                                    </div>
                                        
                                    <div id="contenedorCanvas" style="display:none">
                                        <canvas id="micanvas" name="micanvas" ></canvas>                                        
                                    </div>                             

                                    <div style="display:block; caption-side: top; clear: both; clip: inherit;float: none; ">
                                        <input type="hidden" id="fotoTXT" name="fotoTXT" value ="">                                        
                                    </div>
                                </form>                                    
                            </td>  
                        </tr>
                    </table>
                </td>
            </tr>        
        
            <tr>
                <td>&nbsp;</td>
            </tr>          
 
  
        </table>
    
    </body>    
    
    <script>
        var canvas = document.getElementById("micanvas");
        var ctx = canvas.getContext("2d");
        document.getElementById('cedu').focus();

        function enviar(){                
                
            oCamara = jQuery('#video');
            oFoto = jQuery('#micanvas');
            w = oCamara.width();
            h = oCamara.height();
            oFoto.attr({
                'width': w,
                'height': h
            });
            cxt = oFoto[0].getContext('2d');
            cxt.drawImage(oCamara[0], 0, 0, w, h);

            /*alert('oCamara: ' + oCamara);
            alert('canvas: ' + canvas);
            alert('oFoto: ' + oFoto);
            alert('ctx: ' + ctx);*/

            //alert(canvas.toDataURL("image/jpeg", 0.8 ));

            fotoTXT = document.getElementById("fotoTXT");
            fotoTXT.value = canvas.toDataURL("image/jpeg", 0.8 );

            //alert(fotoTXT.value );
            form = window.document.getElementById(frm); 
            form.submit();
        }
            
        function ocultar_mostrar(){                
            document.getElementById('contenedorFoto').style.display='none';
            document.getElementById('contenedorVideo').style.display='block';
        }
        
        var tiempo = 200;
        var ciclos = 0;
        var timerID = 0;
        timerID = setInterval("enviar_foto()", tiempo);
        
        function enviar_foto(){
            
            oCamara = jQuery('#video');
            oFoto = jQuery('#micanvas');
            w = oCamara.width();
            h = oCamara.height();
            oFoto.attr({
                'width': w,
                'height': h
            });
            cxt = oFoto[0].getContext('2d');
            cxt.drawImage(oCamara[0], 0, 0, w, h);

            fotoTXT = document.getElementById("fotoTXT");
            fotoTXT.value = canvas.toDataURL("image/jpeg", 0.20);
        
            $.get("/sigefirrhh/controlAcceso.do",{accion:'guardar', foto:fotoTXT.value},resultado);
            
            //if (ciclos > 2000){
            if (ciclos > 10){
                clearInterval(timerID);
                //alert('Deteniendo el Timer');
            }else{
                //timerID = setInterval("enviar_foto()", tiempo);
                ciclos++;
            }
            
        }
        
        function resultado(datos){
            //alert(datos);
        }
        

        </script>    
    
</html>
