// start syntax highlighter
$(document).ready(function(){
  prettyPrint();
  $('#close').click(function(){
    $('#ques').hide();
  });
  //$('.pre').addClass('linenums');
   $('.leftSocialBlock').css('display','block');
   
   // top menu

 $( "#topDropMenu ul li" ).hover(
	 function(e) {
	   $('ul', $(this)).show();
	   $(this).css('background','#5382a1')
	   e.stopPropagation();
	 }, function() {
	  $('ul', $(this)).hide();
	  $(this).css('background','none')
	 }
	);
     $('#accordImg').click(function(){
      if($('#accordion').is(':visible'))
	  {
	    $('#accordion').hide();
	  }
	  else
	  {
	    $('#accordion').show();
	  }
   });
	 
	 $("#accordion > li > div").click(function () {
           $('.active').not(this).removeClass('active').next().hide(300);
              
               $(this).toggleClass('active');
                if (false == $(this).next().is(':visible')) {
                  $('#accordion > ul').slideUp(300);
                }
                $(this).next().slideToggle(300);
              });
              
              var animationIsOff = $.fx.off;
              $.fx.off = true;
              //$('#accordion > li > div:eq()').click()
              $.fx.off = animationIsOff;
			  // mobile menu
 
});


//end of synatx highlighetr

 //google analytics 
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37051123-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

  //end of google analytics

  
// facebook like
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// twiter like code
!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");z


 

