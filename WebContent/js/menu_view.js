$(document).ready(function(){
    $('#idmenu').mnmenu();
    //Create and init additional menus
    var $demoContainer = $('#demoContainer');
    $demoContainer.append($("<h3>Regular menus</h3>"));
    $demoContainer.append($("<p>Menus with different colors and styling.</p>"));
    //Create lists
    $(["bluemenu", "grayround", "squarewhite", "blue2menu"]).each(function() {
        var color = this;
        var $opciones_menu = generateMenu(color);
        $demoContainer.append($opciones_menu);
        $opciones_menu.mnmenu({menuClassName: color.toString()});
    });
    $demoContainer.append($(["<h3>Right aligned menu</h3>"].join("")));
    $demoContainer.append($("<p>This menu is the reversed version of the top menu."
            + " Menus will be displayed from bottom to top and from right to left.</p>"));
    //Init right to left menu with custom level settings
    var rightMenuLevelSettings = {};
    //Defaults
    rightMenuLevelSettings[0] = new MNLevelSettings();
    rightMenuLevelSettings[0].parentAttachmentPosition = "SW";
    rightMenuLevelSettings[0].attachmentPosition = "SE";
    //First level
    rightMenuLevelSettings[1] = new MNLevelSettings();
    rightMenuLevelSettings[1].parentAttachmentPosition = "NE";
    rightMenuLevelSettings[1].attachmentPosition = "SE";
    var $rightbtmenu = generateMenu("rightbtmenu");
    $demoContainer.append($rightbtmenu);
    $rightbtmenu.mnmenu({menuClassName: "rightbtmenu", levelSettings: rightMenuLevelSettings});

    /////////////////////////////////////////////////////
    function generateMenu(id) {
        var $opciones_menu = $(["<ul id='", id, "'></ul>"].join(""));
        //ADD Submenus
        for (var i = 1; i < 6; i++) {
            var label = ["Level-", i].join("");
            var $firstLevel = $(["<li>", label, "</li>"].join(""));
            $opciones_menu.append($firstLevel);
            addLevels(1, 5, $firstLevel, label);
        }
        return $opciones_menu;
    }
    function addLevels(current, max, $component, label) {
        var $newContainer = null;
        for (var i = 1; i < Math.floor(Math.random() * 6) + 1; i++) {
            if ($newContainer === null) {
                $newContainer = $("<ul></ul>");
                $component.append($newContainer);
            }
            var newLabel = [label, "-", i].join("");
            var $newLevel = $(["<li>", newLabel, "</li>"].join(""));
            $newContainer.append($newLevel);
            if (current < max) {
                addLevels(current + 1, max, $newLevel, newLabel);
            }
        }
    }
 });