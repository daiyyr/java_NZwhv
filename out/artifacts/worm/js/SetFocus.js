/* 
 * Grants Online
 * Module: SetFocus.js
 * Purpose:
 *		This script runs for all pages inheriting from ApplicationBasePage
 *	and puts the focus on the first editable field in the page.
 * 
 * ------------------------------------------------------------
 * Copyright Intergen 2003
 * Version:       $Revision: $
 * Last Modified: $Modtime: $
 * By:            $Author: $
 * ------------------------------------------------------------
 * $Log: $
 * 
 * ------------------------------------------------------------
 */


if (isIE())
{
    setFocus();
}

// Utiliy to place the focus on the first editable (enabled)
// control. Not the this implementation currently only works
// with IE().
function setFocus()
{
    if (isIE)
    {
        if (document.forms.length > 0)
        {
            var field = document.forms[0];
            for (i = 0; i < field.length; i++)
            {
                if
                    (
                    (field.elements[i].type == 'text') ||
                    (field.elements[i].type == 'password') ||
                    (field.elements[i].type == 'textarea') ||
                    (field.elements[i].type.toString().charAt(0) == 's')
                    )
                {

                    try
                    {
                        document.forms[0].elements[i].focus();
                        break;
                    }
                    catch (error)
                    {
                    }
                }

            }
        }
    }
}

// Utility to detect whether we are running on IE
function isIE()
{
    return (navigator.userAgent.toLowerCase().indexOf("msie") != -1);
}