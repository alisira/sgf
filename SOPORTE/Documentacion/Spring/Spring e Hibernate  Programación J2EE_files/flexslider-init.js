/*
 * jQuery FlexSlider v2.1
 * http://www.woothemes.com/flexslider/
 *
 * Copyright 2012 WooThemes
 * Free to use under the GPLv2 license.
 * http://www.gnu.org/licenses/gpl-2.0.html
 *
 * Contributing author: Tyler Smith (@mbmufffin)
*/

(function($){
$(window).load(function() {
$('.flexslider').flexslider({
animation: "slide",
controlsContainer: '.flex-container'
});
});
})(jQuery)
/*
For fade function
(function($) {
    $(window).load(function() {
        $('.flexslider').flexslider({
	            animation: "fade",
				controlsContainer: '.flex-container'
	    });
    });
})(jQuery)
*/