$(document).ready
(
    function(){
 
        var canvas = $('#micanvas'),
            cxt = canvas[0].getContext('2d'),
            video = $('#video'),
            video = video[0];
 
        if (navigator.getUserMedia){
            navigator.getUserMedia(
                { 'video': true },
                function(stream){
                    alert('paso getUserMedia');
                    video.src = stream;
                    video.play();
                }
            );
        }else if (navigator.webkitGetUserMedia){
            navigator.webkitGetUserMedia
            (
                { 'video': true },
                function(stream)
                {
                    alert('paso webkitGetUserMedia');
                    video.src = window.webkitURL.createObjectURL(stream);
                    video.play();
                }
            );
        }else if (navigator.mozGetUserMedia){
            //Cuando se usa firefox
            navigator.mozGetUserMedia
            (
                { 'video': true },
                function(stream)
                {
                    //alert('paso mozGetUserMedia');
                    video.mozSrcObject = stream;
                    video.play();
                },
                function(err){
                    //
                    //alert('An error occured! '+err);
                }
            );
        }
        $('#photo').click
        (
            function(){   
                var img = document.getElementById("fotoTrabajador");
                var canvas = document.getElementById("micanvas");
                
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
                
                img.src = canvas.toDataURL("image/jpeg", 0.8 );
                
            }
        );
    }    
);