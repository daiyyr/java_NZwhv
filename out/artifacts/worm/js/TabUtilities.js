/*
 * Module: TabUtilities.js
 * Purpose:
 *		This script provides functionality for tab controls (e.g.
 * the tab controls used by the OnlineApplication WizardPageNavigator
 * and TabHeader controls).
 *
 * ------------------------------------------------------------
 * Copyright Intergen 2005
 * Version:       $Revision: $
 * Last Modified: $Modtime: $
 * By:            $Author: $
 * ------------------------------------------------------------
 * $Log: $
 *
 * ------------------------------------------------------------
 */

// Hooks up the mouse over/mouse off events to the SetCssClassOn
// and SetCssClassOff methods.
function InitTabContainer(tabContainerId)
{
    // avoid mac as it goes a little odd
    if (document.getElementById && document.createElement && (navigator.appVersion.indexOf("Macintosh") < 0))
    {
        // add behaviours - to all the inputs nested inside #tabtable1
        var container = document.getElementById(tabContainerId);
        var linkboxes = container.getElementsByTagName('input');
        for (var i=0;i<linkboxes.length;i++)
        {
            linkboxes[i].onmouseover = SetCssClassOn;
            linkboxes[i].onmouseout = SetCssClassOff;
        }

    }
    else
    {return;}
}

// Changes a HTML element's CSS class to the original classname + "on".
// This method should be used in conjunction with the SetCssClassOff() method.
function SetCssClassOn()
{
    // get target HTML element
    var target = this.id;
    var target = document.getElementById(target);

    // change its CSS class to the "rollover" one - always original classname + "on"
    target.className += "on";

    // change cursor to link cursor
    navigator.userAgent.indexOf("Gecko") > -1 ? target.style.cursor = "pointer" : target.style.cursor = "hand";
}

// Changes a HTML element's CSS class to the original classname by removing "on"
// from the end of the current CSS class. This method should be used in conjunction
// with the SetCssClassOn() method.
function SetCssClassOff()
{
    var target = this.id;
    var target = document.getElementById(target);

    // remove "on" from the end of the classname to set it back to the default CSS class
    target.className = target.className.substring(0,target.className.length - 2);

    target.style.cursor = "default";
}