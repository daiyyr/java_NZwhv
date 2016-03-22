<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate, private" />
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache" />
    <meta HTTP-EQUIV="Expires" CONTENT="-1" />
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />



    <link rel="stylesheet" media="screen" href="elements/css/screen.css" type="text/css" ></link>
    <link rel="stylesheet" media="screen" href="elements/themes/reseller_themes/grey/reseller_screen.css" type="text/css" ></link><link rel="stylesheet" media="screen" href="elements/themes/acquirer_themes/westpac/acquirer_screen.css" type="text/css" ></link><link rel="stylesheet" type="text/css" media="print" href="elements/css/print.css" ></link>
    <link rel="stylesheet" media="print" href="elements/themes/reseller_themes/grey/reseller_print.css" type="text/css" ></link><link rel="stylesheet" media="print" href="elements/themes/acquirer_themes/westpac/acquirer_print.css" type="text/css" ></link><link rel="stylesheet" type="text/css" media="handheld" href="elements/css/mobile.css" ></link>
    <link rel="stylesheet" media="handheld" href="elements/themes/reseller_themes/grey/reseller_mobile.css" type="text/css" ></link><link rel="stylesheet" media="handheld" href="elements/themes/acquirer_themes/westpac/acquirer_mobile.css" type="text/css" ></link><!--[if IE 6]><link rel="stylesheet" href="elements/css/ie6.css" media="screen" type="text/css" /><script type="text/javascript" src="elements/js/iepngfix_tilebg.js"></script><![endif]-->
    <meta name="viewport" content="width=device-width" ></meta>
    <title>Paymark Merchant Payment</title>
</head>


<body >

<ul id="accessibilty">
    <li><a href="#content">Skip to content</a></li>
</ul>


<!--Start of header Region-->
<div id="headerContainer">
    <div id="headerContent">
        <h1><a href="./index.php" title="Home"></a></h1>
        <h2><a href="http://www.paystation.co.nz" title="Visit Paystation">Powered By Paystation</a></h2>

    </div>
    <div id="titlerContainer">
        <div id="titleContent">
            <h3></h3>
        </div>
    </div>
</div>
<!--End of header Region-->

<!--Start of Credit Card Entry Content Region-->
<div id="contentContainer">
    <div id="content">
        <div id="purchaseData">
            <p>Amount: <span>NZD &#36;165.00</span>Card Type: <span>VISA</span></p>
        </div>
        <div class="yellowBox clearfix"> <a name="content"></a>
            <h3>2: Enter Your Card Details</h3>


            <div id="formContainer">
                <form action="?hkc=hzzMAafYc9Mazx3KFFy0vOAWqds0F4NRHV4ovoEoZ_Q&rm=14310604811980903474" method="POST" name="credit_card_entry" >

                    <fieldset>
                        <legend>Credit Card Information</legend>
                        <div id=cardnumberblock>
                            <div id=cardnumberlabel>
                                <label for="cardnumber" >Card Number:</label>
                            </div>
                            <div>
                                <!--type="text"   change for html5 browser input -->
                                <input type="tel" pattern="[0-9]*" id="cardnumber" name="cardnumber"  tabindex="1" title="card number" autocomplete="off" value="">

                            </div>
                        </div>
                        <input type=hidden id=use_card_security_code name=use_card_security_code value="Y">
                        <input type=hidden id=enforce_card_security_code name=enforce_card_security_code value="N">
                        <input type=hidden id=enforce_card_security_code_3party name=enforce_card_security_code_3party value="Y">
                        <input type=hidden id=enforce_card_security_code_futurepay name=enforce_card_security_code_futurepay value="Y">
                        <div id=cardverificationblock>
                            <div id=cardverificationlabel>
                                <label for="cardverification" >Card Security Code *</label>
                            </div>
                            <div class="clear"></div>
                            <div>
                                <input type="tel" pattern="[0-9]*" id="cardverificationcode"  name="cardverificationcode" tabindex="4" title="card verification code" autocomplete="off">
                                <img src="elements/img/icon_crd.gif" alt="CVV placement" class="cvv" align="middle">

                            </div>


                        </div>
                        <div class="clear"></div>
                        <div id=expirydateblock>
                            <div id=expirydatelabel>
                                <label for="expirydate" >Expiry Date: </label>
                            </div>

                            <div>
                                <select id="expirymonth" name="expirymonth" tabindex="2">
                                    <option value='01'  >01</option><option value='02'  >02</option><option value='03'  >03</option><option value='04'  >04</option><option value='05'  >05</option><option value='06'  >06</option><option value='07'  >07</option><option value='08'  >08</option><option value='09'  >09</option><option value='10'  >10</option><option value='11'  >11</option><option value='12'  >12</option>			  </select>
                                <select id="expiryyear" name="expiryyear" tabindex="3">
                                    <option value='2015'  >2015</option><option value='2016'  >2016</option><option value='2017'  >2017</option><option value='2018'  >2018</option><option value='2019'  >2019</option><option value='2020'  >2020</option><option value='2021'  >2021</option><option value='2022'  >2022</option><option value='2023'  >2023</option><option value='2024'  >2024</option><option value='2025'  >2025</option><option value='2026'  >2026</option><option value='2027'  >2027</option><option value='2028'  >2028</option><option value='2029'  >2029</option>			  </select>



                            </div>
                            <div id=cardverificationdesc><label for="cardverification" >* The 3 digit code after the card number <br>on the signature panel of your card</label></div>

                            <input type=hidden name="hk" id="hk" value="hzzMAafYc9Mazx3KFFy0vNDPvFZlu1FkWs7N6J7OZak">
                            <input type=hidden name="hosted_responsive_format" id="hosted_responsive_format" value="N">
                            <input type=hidden name="cardtype" id="cardtype" value="VISA">
                            <input type="hidden" id=future_pay name=future_pay value='N'>
                            <input type="hidden" id=future_pay_save_only name=future_pay_save_only value=''>

                        </div>
                        <div class="clear"></div>
                        <div id="cardholdernameblock">
                            <div id="cardholdernamelabel">
                                <label for="cardholder" >Name of Cardholder:</label>
                            </div>
                            <div>
                                <!--type="text"   change for html5 browser input -->
                                <input type="text" id="cardholder" name="cardholder" tabindex="5" title="cardholder" autocomplete="off" value="">
                            </div>
                        </div>


                    </fieldset>

                    <!--input type="hidden" id=processingStage name=processingStage value='processing' -->
                    <input name="pay_button" id="pay_button" type="submit" value="Pay Now" class="submit" tabindex="5" onclick="this.disabled=true;this.value='Submitting...';this.form.submit();">
                    <input name="pay_now" id="pay_now" type="hidden" value="Pay Now">

                </form>
            </div>
        </div>
    </div>

    <!--End of Credit Card Entry Content Region-->
    <!--Start of Content Region-->
    <div style="clear:both"></div>
    <div id="footerContainer">
        <div id="footerContent">
            <!--div class="paystation"><a href="http://www.paystation.co.nz/" target="_blank" title="Paystation"></a></div-->

            <div id="paymark_thawte" ><a href="http://www.paymark.co.nz" title="Paymark" target="_blank"><img border=0 src="/hosted/elements/img/footer_pm.gif"></a>
                <a href="http://www.thawte.com/" title="thawte 100% Secure" target="_blank"><img class="thawte" border=0 src="/hosted/elements/img/footer_tht.gif"></a>
            </div>
            <div id="disclaimer"><p>Your payment details have the security offered by Paymark, a fully AIS PCI DSS compliant Certified Solutions Provider (CSP), using 128-bit SSL encryption.  Credit Card details will be sent directly to the acquiring institution for processing.  Full card data is not available to or used by the  merchant.  You will be issued with a receipt number at the end of your transaction.</p></div>


        </div>
    </div>

    <!--End of Content Region-->

</body>
</html>
