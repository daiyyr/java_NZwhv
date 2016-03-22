//$(document).ready(function(){   
window.onload=function(){
    //bit of javascript to push footer to the bottom of the screen
    var winH = $(window).height();
    var banH = $("#pageHeader").height() + $("#featureBanner").height();
    var footH = $("#footerWrapper").height();
    var contH = $(".colleft").height();

    var reqH = winH - banH - (footH);

    if (contH < reqH )
        $(".colleft").css('height', reqH);


    //other functions
    $(".sideBarBox").each(function(){
        if (jQuery.trim($(this).html()) == "") {
            $(this).remove();
        }
    });

    //remove right borders from new styled tables.
    $(".newStyle tr").each(function() {
        $(this).children("td").last().addClass("noBorder");
    });

    if ($("body").attr("onbeforeunload")) {
        $("#pageWrapper").addClass("editMode");
    } else {
        $("#pageWrapper").addClass("viewMode");
    }

    //enable image slider
    $('#slider').cycle({
        fx: 'fade' ,
        speed:   2000,
        timeout: 10000,
        pager:  '#thumbs',
        fit: 1,
        height: 'auto',
        width: 'auto'
    });

    //move text inside thumbnail images.
    $('#thumbs a').html('');



};