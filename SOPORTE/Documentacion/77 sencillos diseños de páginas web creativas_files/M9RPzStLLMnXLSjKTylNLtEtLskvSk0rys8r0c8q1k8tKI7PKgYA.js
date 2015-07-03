function objectWithPrototype(g, j)
{
    var h, k;

    function i()
    {
    }
    i.prototype = g;
    h = new i();
    h.prototype = g;
    if (typeof j !== "undefined")
    {
        for (k in j)
        {
            if (j.hasOwnProperty(k))
            {
                h[k] = j[k]
            }
        }
    }
    return h
}
var Magnifier =
{
    positionMagnifierNextTo: function (g)
    {
        var h, i, f;
        h = this.magnifierDiv();
        i = jQuery(g).offset().top + jQuery(g).outerHeight() - h.outerHeight();
        if (i < jQuery(window).scrollTop())
        {
            i = jQuery(window).scrollTop()
        }
        if ((jQuery(g).offset().left + (jQuery(g).outerWidth() / 2)) >= (jQuery(window).width() / 2))
        {
            f = jQuery(g).offset().left - h.outerWidth()
        }
        else
        {
            f = jQuery(g).offset().left + jQuery(g).outerWidth()
        }
        h.css(
        {
            top: i,
            left: f
        })
    },
    showMagnifier: function (b)
    {
        if (jQuery(b).attr("data-tooltip") === undefined)
        {
            jQuery(b).attr("data-tooltip", jQuery(b).attr("title"));
            jQuery(b).attr("title", "")
        }
        this.populateMagnifierFrom(b);
        this.positionMagnifierNextTo(b);
        this.magnifierDiv().show()
    },
    hideMagnifier: function ()
    {
        this.magnifierDiv().hide()
    },
    magnify: function (d)
    {
        var c = this;
        jQuery(d).live("mouseenter", function ()
        {
            c.showMagnifier(this)
        });
        jQuery(d).live("mouseleave", function ()
        {
            c.hideMagnifier(this)
        })
    }
};
var TooltipMagnifier = objectWithPrototype(Magnifier, {
    magnifierDiv: function ()
    {
        return jQuery("div#tooltip-magnifier")
    },
    populateMagnifierFrom: function (b)
    {
        this.magnifierDiv().html("<p>" + jQuery(b).attr("data-tooltip") + "</p>")
    }
});
var ImageMagnifier = objectWithPrototype(Magnifier, {
    populateMagnifierFrom: function (g)
    {
        var i, h, f;
        i = this.magnifierDiv();
        h = i.find("div.size-limiter");
        f = new Image();
        jQuery(f).attr("src", jQuery(g).attr("data-preview-url"));
        if (jQuery(g).attr("data-preview-height"))
        {
            jQuery(f).attr("height", 300);
            jQuery(f).attr("width", 300 / jQuery(g).attr("data-preview-height") * jQuery(g).attr("data-preview-width"))
        }
        jQuery(h).empty();
        jQuery(h).append(f)
    }
});
var LandscapeImageMagnifier = objectWithPrototype(ImageMagnifier, {
    magnifierDiv: function ()
    {
        return jQuery("div#landscape-image-magnifier")
    }
});
var SquareImageMagnifier = objectWithPrototype(ImageMagnifier, {
    magnifierDiv: function ()
    {
        return jQuery("div#square-image-magnifier")
    }
});
var SmartImageMagnifier = objectWithPrototype(ImageMagnifier, {
    magnifierDiv: function ()
    {
        return jQuery("div#smart-image-magnifier")
    }
});
var PortraitImageMagnifier = objectWithPrototype(ImageMagnifier, {
    magnifierDiv: function ()
    {
        return jQuery("div#portrait-image-magnifier")
    }
});
jQuery(function ()
{
    TooltipMagnifier.magnify("img.tooltip-magnifier");
    LandscapeImageMagnifier.magnify("img.landscape-image-magnifier");
    SquareImageMagnifier.magnify("img.square-image-magnifier");
    SmartImageMagnifier.magnify("img.smart-image-magnifier");
    PortraitImageMagnifier.magnify("img.portrait-image-magnifier")
});