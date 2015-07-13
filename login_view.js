$(document).ready(function(){
    $("#sumitir").click(function() {
        login = $("#login").val();
        password = $("#password").val();
        validacion_login = /^[a-z\d_]{3,14}$/;
        validacion_password = /^[a-z\d_]{3,14}$/;

        if (login == "" || !validacion_login.test(login)){
            $("#login").focus();
            alert('Login Invalido, Favor Corregir');
            return false;
        }else if(password == "" || !validacion_password.test(password)){
            $("#password").focus();
            alert('Contraseña Invalida, Favor Corregir');
            return false;
        }else{
            return true;
        }
    });
    $("#cambiar_password").click(function() {
         
        login = $("#login").val();
        password = $("#password").val();
        newpassword = $("#newpassword").val();
        confirmapassword = $("#confirmapassword").val();

        validacion_login = /^[a-zA-Z]{1}[a-z\d_]{3,14}$/;
        validacion_password = /^[a-zA-Z]{1}[a-z\d_]{3,14}$/;

        if (login == "" || !validacion_login.test(login)){
            $("#login").focus();
            alert('Login Invalido, Favor Corregir');
            return false;
        }else if(password == "" || !validacion_password.test(password)){
            $("#password").focus();
            alert('Contraseña Invalida, Favor Corregir');
            return false;
        }else if(newpassword == "" || !validacion_password.test(newpassword)){
            $("#newpassword").focus();
            alert('Nueva Contraseña es Invalida, Favor Corregir');
            return false;
        }else if(confirmapassword == "" || !validacion_password.test(confirmapassword)||
              newpassword != confirmapassword){
            $("#confirmapassword").focus();
            alert('Error en la confirmacion de contraseña, Favor Corregir');
            return false;            
        }else{
            return true;
        }
    });
});

function alternar(){

    if(document.getElementById('tr_contrasena').style.display=='none'){
        document.getElementById('tr_contrasena').style.display='';
        document.getElementById('tr_contrasena02').style.display='';
        document.getElementById('tr_newcontrasena').style.display='';
        document.getElementById('tr_newcontrasena02').style.display='';
        document.getElementById('botones_new').style.display='';
        document.getElementById('botones_new02').style.display='';
        document.getElementById('botones_normal').style.display='none';
        document.getElementById('botones_normal02').style.display='none';
        //document.getElementById("rowdinamico").rowSpan="10";
        var left = document.getElementById('rowdinamico');
        left.setAttribute('rowspan', '10');

    }else{

        document.getElementById('login').value = '';
        document.getElementById('password').value = '';
        document.getElementById('newpassword').value = '';
        document.getElementById('confirmapassword').value = '';

        document.getElementById('tr_contrasena').style.display='none';
        document.getElementById('tr_contrasena02').style.display='none';

        document.getElementById('tr_newcontrasena').style.display='none';
        document.getElementById('tr_newcontrasena02').style.display='none';

        document.getElementById('botones_new').style.display='none';
        document.getElementById('botones_new02').style.display='none';
        document.getElementById('botones_normal').style.display='';
        document.getElementById('botones_normal02').style.display='';
        //document.getElementById("rowdinamico").rowSpan="7";
        var left = document.getElementById('rowdinamico');
        left.setAttribute('rowspan', '7');
    }
};

function foco_lg(){
    document.getElementById('login').focus();
}

function foco2(){
    document.getElementById('newpassword').focus();
}      