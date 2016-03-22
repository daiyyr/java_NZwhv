package eru;


import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import net.sourceforge.htmlunit.corejs.javascript.EvaluatorException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dyyr on 2015/4/16.
 * search 'dyyr' to find the code that need to be improved
 */



/*
    9 forms had been submitted, pay now...
    5 paying success
    4 have benn checked again, now choose the pay way
    3 choose to pay now
    2 for success for choosing to pay later at a bank
    1 for success;
    0 connection time out;
    -1 elements not found;
    -2 losing login session;
    -3 there is no earlier apply;
    -4 for getting the messy JS page;
    -5 there is an earlier application;
    -6 application not open
    //新增、修改apply页需增加国家未开放判断 dyyr
    -7 pay button missing
    -8 apply not finished
    -9 first element not found
*/
public class Worm {
    public static enum WormUser{TEST_dudeea, TEST_hansha, TEST_tadarcy, TRUE_daiyyr, TRUE_yelushi}

//====================================================================
    private static WormUser wormUser_= WormUser.TEST_dudeea;
//====================================================================

    public static String root="";
    private static List<NameValuePair> userFields;

    public static String file2string(String source){
        File file;
        if (root.equals("")){
            file = new File(Worm.class.getResource(source).getFile());
        }else{
            file = new File(root +"\\"+ source);
        }
        String temp="", s="";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                s += temp;
                s += "\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return s;
    }

    public static List<NameValuePair> loadUserFields(){
        String str = file2string("user/" + wormUser_.toString());
        List<NameValuePair> fields = new ArrayList<NameValuePair>();
        String[] strs = str.split("\\n");
        for (String s : strs) {
            String[] t = s.split(" ",2);
            fields.add(new NameValuePair(t[0],t[1]));
        }
        return fields;
    }

    public static String cookies2json(Set<Cookie> cookies){
        String r = "[";
        Iterator<Cookie> it = cookies.iterator();
        Cookie c;
        while(it.hasNext()) {
            c = it.next();
            r += "{";
        }

        return r;
    }

    private static WebClient webclient;
    private static Pattern messyJS, okResult;
    private static Matcher m;
    private static String pagestr, applicationId, editURL=null;
    private static HtmlPage rootPage, applyPage,
    personal1, personal2, medical1, character, workingHolidaySpecific, allFormsOK, checkpage, choosePayWay, pay1, pay2, pay3, pay4, pay5 ;
    private static HtmlInput username;
    private static HtmlInput password;
    private static HtmlInput applynowbutton;

    //Personal1
    private static HtmlInput ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox;
    private static HtmlSelect ctl00_ContentPlaceHolder1_personDetails_genderDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day;
    private static HtmlSelect ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month;
    private static HtmlSelect ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year;
    private static HtmlSelect ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList;
    private static HtmlInput ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox;
    private static HtmlSelect ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList;
    private static HtmlInput ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox;
    private static HtmlSelect ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist;
    private static HtmlSelect ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist;
    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageHeader_nav_pageTabs_TabHeaders_ctl01_tabButton;

    //Personal2
    private static HtmlInput ctl00_ContentPlaceHolder1_identification_passportNumberTextBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month;
    private static HtmlSelect ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year;
    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton;

    //Medical1
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList;
    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton;

    //Character
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_removalOrderDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_deportedDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_chargedDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_convictedDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_excludedDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_character_removedDropDownList;
    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton;

    //WorkingHolidaySpecific
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList;
    private static HtmlSelect ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList;

    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton;
    private static HtmlInput ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton;

    //check
    private static HtmlInput ctl00_ContentPlaceHolder1_falseStatementCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_notesCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_circumstancesCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_warrantsCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_informationCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_healthCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_adviceCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_registrationCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_entitlementCheckbox;
    private static HtmlInput ctl00_ContentPlaceHolder1_permitExpiryCheckBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox;

    private static HtmlInput ctl00_ContentPlaceHolder1_payLaterBranchImageButton;
    private static HtmlInput ctl00_ContentPlaceHolder1_submitImageButton;

    private static HtmlInput ctl00_ContentPlaceHolder1_payLaterImage;
    private static HtmlInput ctl00_ContentPlaceHolder1_payNowImage;
    private static HtmlAnchor ctl00_ContentPlaceHolder1_payAnchor;

    private static HtmlInput ctl00_ContentPlaceHolder1_payorNameTextBox;
    private static HtmlInput ctl00_ContentPlaceHolder1_okImageButton;

    private static HtmlInput card_type_VISA;
    private static HtmlInput cardnumber;
    private static HtmlInput cardverificationcode;
    private static HtmlInput expirymonth;
    private static HtmlInput expiryyear;
    private static HtmlInput cardholder;
    private static HtmlInput pay_button;

    private static HtmlAnchor editAnchor,
    ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink,
    ctl00_ContentPlaceHolder1_onlinePaymentAnchor;
    private static CookieManager cm;

    private int getLoginPage()throws Exception{
        try {
            rootPage = webclient.getPage("https://www.immigration.govt.nz/secure/Login+Working+Holiday.jsp");
            if (rootPage.asXml().contains("OnlineServicesLoginStealth_VisaLoginControl_userNameTextBox")) {
                System.out.println("getting login page succeed!");
                Set<Cookie> a = cm.getCookies();
                return 1;
            }
            else{
                return 0;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("HttpHostConnectException as 'getLoginPage', request again...");
                return 0;
            }
            if (e instanceof UnknownHostException){
                System.out.println("UnknownHostException as 'getLoginPage', request again...");
                return 0;
            }
            throw e;
        }
    }
    private int login(String user, String pw) throws Exception{
        try {
            username = (HtmlInput)rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_userNameTextBox");
            password = (HtmlInput)rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_passwordTextBox");
            if (password == null) {
                System.out.println("password input not found, refresh the page...");
                return -1;
            }
            HtmlInput loginB = (HtmlInput)rootPage.getElementById("OnlineServicesLoginStealth_VisaLoginControl_loginImageButton");
            if (loginB == null) {
                System.out.println("loginButton input not found, refresh the page...");
                return -1;
            }
            username.setValueAttribute(user);
            password.setValueAttribute(pw);
            loginB.click();
            Set<Cookie> b = cm.getCookies();

            System.out.println(b.toString());
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("connection time out when login" + user + ", now login again...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int getApplyPage()throws Exception{
        try {
            applyPage = webclient.getPage("https://www.immigration.govt.nz/WORKINGHOLIDAY/Application/Create.aspx?CountryId=82");
//            applyPage = webclient.getPage("https://www.immigration.govt.nz/WORKINGHOLIDAY/Application/Create.aspx?CountryId=46");
//            applyPage = webclient.getPage("https://www.immigration.govt.nz/WORKINGHOLIDAY/Application/Create.aspx?CountryId=104");
            pagestr = applyPage.asXml();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_applyNowButton")){
                System.out.println("getting applyNow page succeed");
            }

            if ( pagestr.contains("Access denied") ) {
                System.out.println("login session missing when 'getApplyPage', login again...");
                return -2;
            }

            //不会出现这个，会出现applynow，点击后将出错 dyyr
            if ( pagestr.contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_editHyperLink") ) {
                System.out.println("There is an earlier application, editApply...");
                return -5;
            }
            if ( pagestr.contains("Unfortunately the available places for this Working Holiday Scheme have been filled")
                    ||
                    pagestr.contains("LoginApplyOnlineLink")
                    ) {
                System.out.println("Application is not open, hang on...");
                return -6;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("connection time out as 'getApplyPage', request again...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int applynow()throws Exception{
        try {
            applynowbutton = (HtmlInput)applyPage.getElementById("ctl00_ContentPlaceHolder1_applyNowButton");
            if (applynowbutton == null) {
                System.out.println("applynow button not found, refresh the page...");
                return -1;
            }
            personal1 = applynowbutton.click();
//            System.out.println("dyyr test click applynow:\n"+personal1.getUrl()+"\n"+personal1.asXml());
            if(personal1.asXml().contains("Multiple applications are not supported. All previous applications must be lodged before a new one can be created.")){
                System.out.println("There is an earlier application, editApply...");
                return -5;
            }
            editURL = personal1.getUrl().toString();
            Pattern p1 = Pattern.compile("(?<=ApplicationId=)\\d*");
            Matcher m1 = p1.matcher(editURL);
            if(m1.find()) {
                applicationId = m1.group();
            }
            m = messyJS.matcher(personal1.asXml());
            if (m.find()){
                System.out.println("getting personal1 with the messy JS page, click apply now button again");
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("connection time out as 'applynow', request again...");
                return 0;
        }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int editApply() throws Exception{
        try {
            if((editURL!=null) && editURL.contains("ApplicationId="))
                personal1 = webclient.getPage(editURL);
            else {
                applyPage = webclient.getPage("https://www.immigration.govt.nz/workingholiday/");
                if (applyPage.asXml().contains("Access denied")) {
                    System.out.println("login session missing when 'editApply', login again...");
                    return -2;
                }
                if (applyPage.asXml().contains("Please select your country from the list below and click the OK button")) {
                    System.out.println("there is no earlier apply when 'editApply', start an apply ...");
                    return -3;
                }

                editAnchor = (HtmlAnchor) applyPage.getElementById("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_editHyperLink");
                if ((editAnchor == null)) {
                    if (applyPage.asXml().contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink")){
                        System.out.println("forms had been submitted, pay now...");
                        return 9;
                    }
                    System.out.println("edit apply anchor not found, refresh the page...");
                    return -1;
                }
                Pattern p1 = Pattern.compile("(?<=ApplicationId=)\\d*");
                Matcher m1 = p1.matcher(applyPage.asXml());
                m1.find();
                applicationId = m1.group();
                editURL = "https://www.immigration.govt.nz/WorkingHoliday/Wizard/Personal1.aspx?ApplicationId="
                + applicationId
                + "&IndividualType=Primary&IndividualIndex=1";
                personal1 = editAnchor.click();
            }
            pagestr = personal1.asXml();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox")){
                System.out.println("getting personal1 page succeed!");
                return 1;
            }

            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting personal1 with the messy JS page, start anew...");
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("connection time out when 'editApply', now apply again...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int fillPersonal1() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox = (HtmlInput)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox");
            if (ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox input not found, refresh the page...");
                System.out.println(personal1.asXml());
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox = (HtmlInput)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox");
            if (ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_genderDropDownList = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_genderDropDownList");
            if (ctl00_ContentPlaceHolder1_personDetails_genderDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_genderDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day");
            if (ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month");
            if (ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year");
            if (ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList");
            if (ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox = (HtmlInput)personal1.getElementById("ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox");
            if (ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox = (HtmlInput)personal1.getElementById("ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox");
            if (ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList");
            if (ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox = (HtmlInput)personal1.getElementById("ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox");
            if (ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist");
            if (ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist == null) {
                System.out.println("ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList");
            if (ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist = (HtmlSelect)personal1.getElementById("ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist");
            if (ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist == null) {
                System.out.println("ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_wizardPageHeader_nav_pageTabs_TabHeaders_ctl01_tabButton =
                    (HtmlInput) personal1.getElementById("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_pageTabs_TabHeaders_ctl01_tabButton");


            ctl00_ContentPlaceHolder1_personDetails_familyNameTextBox.setValueAttribute("lanye22");
            ctl00_ContentPlaceHolder1_personDetails_givenName1Textbox.setValueAttribute("lanlu");
            ctl00_ContentPlaceHolder1_personDetails_genderDropDownList.setSelectedAttribute("M", true);
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Day.setSelectedAttribute("25", true);
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Month.setSelectedAttribute("2", true);
            ctl00_ContentPlaceHolder1_personDetails_dateOfBithDatePicker_Year .setSelectedAttribute("1988", true);
            ctl00_ContentPlaceHolder1_personDetails_CountryDropDownList .setSelectedAttribute("82", true);
            ctl00_ContentPlaceHolder1_addressContactDetails_address_address1TextBox .setValueAttribute("Wernsdorfer Str.");
            ctl00_ContentPlaceHolder1_addressContactDetails_address_cityTextBox .setValueAttribute("Berlin");
            ctl00_ContentPlaceHolder1_addressContactDetails_address_countryDropDownList .setSelectedAttribute("82", true);
            ctl00_ContentPlaceHolder1_addressContactDetails_contactDetails_emailAddressTextBox .setValueAttribute("296785249@qq.com");
            ctl00_ContentPlaceHolder1_hasAgent_representedByAgentDropdownlist .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_communicationMethod_communicationMethodDropDownList .setSelectedAttribute("1", true);
            ctl00_ContentPlaceHolder1_hasCreditCard_hasCreditCardDropDownlist .setSelectedAttribute("Yes", true);

            personal2 = ctl00_ContentPlaceHolder1_wizardPageHeader_nav_pageTabs_TabHeaders_ctl01_tabButton.click();
            if(personal2.asXml().contains("ctl00_ContentPlaceHolder1_identification_passportNumberTextBox")){
                System.out.println("fill in personal1 succeed!");
                return 1;
            }
            if(personal2.asXml().contains("We're sorry, this page is currently unavailable")){
                System.out.println("personal2 page is unavailable, click again..");
                return 0;
            }
            m = messyJS.matcher(personal2.asXml());
            if (m.find()){
                System.out.println("getting personal2 with the messy JS page");
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                System.out.println("connection time out when fill personal1, reload...");
                //需处理reload此页情况 dyyr
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int fillPersonal2() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_identification_passportNumberTextBox = (HtmlInput)personal2.getElementById("ctl00_ContentPlaceHolder1_identification_passportNumberTextBox");
            if (ctl00_ContentPlaceHolder1_identification_passportNumberTextBox  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_passportNumberTextBox input not found, refresh the page...");
                System.out.println("dyyr test p2"+personal2.asXml());
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox  = (HtmlInput)personal2.getElementById("ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox");
            if (ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day");
            if (ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month");
            if (ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year");
            if (ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist");
            if (ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day");
            if (ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month");
            if (ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year");
            if (ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day");
            if (ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month  = (HtmlSelect) personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month");
            if (ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year  = (HtmlSelect)personal2.getElementById("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year");
            if (ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton = (HtmlInput)personal2.getElementById("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton");
            if (ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton input not found, refresh the page...");
                return -1;
            }

            ctl00_ContentPlaceHolder1_identification_passportNumberTextBox.setValueAttribute("3366084");
            ctl00_ContentPlaceHolder1_identification_confirmPassportNumberTextBox .setValueAttribute("3366084");
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Day.setSelectedAttribute("18", true);
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Month.setSelectedAttribute("8", true);
            ctl00_ContentPlaceHolder1_identification_passportExpiryDateDatePicker_Year.setSelectedAttribute("2018", true);
            ctl00_ContentPlaceHolder1_identification_otherIdentificationDropdownlist.setSelectedAttribute("3", true);
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Day.setSelectedAttribute("8", true);
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Month.setSelectedAttribute("5", true);
            ctl00_ContentPlaceHolder1_identification_otherIssueDateDatePicker_Year.setSelectedAttribute("1995", true);
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Day.setSelectedAttribute("8", true);
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Month .setSelectedAttribute("5", true);
            ctl00_ContentPlaceHolder1_identification_otherExpiryDateDatePicker_Year .setSelectedAttribute("2019", true);

            medical1 =ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl01_tabButton.click();
            if(medical1.asXml().contains("ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList")){
                System.out.println("fill in personal2 succeed!");
                return 1;
            }
            m = messyJS.matcher(medical1.asXml());
            if (m.find()){
                System.out.println("getting medical1 with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when fill personal2, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int fillMedical1() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList  = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList  = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList  = (HtmlSelect) medical1.getElementById("ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList");
            if (ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton = (HtmlInput) medical1.getElementById("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton");
            if (ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton input not found, refresh the page...");
                return -1;
            }

            ctl00_ContentPlaceHolder1_medicalConditions_renalDialysisDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_tuberculosisDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_cancerDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_heartDiseaseDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_disabilityDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_hospitalisationDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_residentailCareDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_medicalConditions_tbRiskDropDownList .setSelectedAttribute("Yes", true);

            character =ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl02_tabButton.click();
            if(character.asXml().contains("ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList")){
                System.out.println("fill in medical1 succeed!");
                return 1;
            }
            m = messyJS.matcher(character.asXml());
            if (m.find()){
                System.out.println("getting character page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when fill fillMedical1, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int fillCharacter() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList");
            if (ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList  = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList");
            if (ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_removalOrderDropDownList  = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_removalOrderDropDownList");
            if (ctl00_ContentPlaceHolder1_character_removalOrderDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_removalOrderDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_deportedDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_deportedDropDownList");
            if (ctl00_ContentPlaceHolder1_character_deportedDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_deportedDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_chargedDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_chargedDropDownList");
            if (ctl00_ContentPlaceHolder1_character_chargedDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_chargedDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_convictedDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_convictedDropDownList");
            if (ctl00_ContentPlaceHolder1_character_convictedDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_convictedDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList");
            if (ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_excludedDropDownList  = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_excludedDropDownList");
            if (ctl00_ContentPlaceHolder1_character_excludedDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_excludedDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_character_removedDropDownList = (HtmlSelect) character.getElementById("ctl00_ContentPlaceHolder1_character_removedDropDownList");
            if (ctl00_ContentPlaceHolder1_character_removedDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_character_removedDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton = (HtmlInput) character.getElementById("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton");
            if (ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton input not found, refresh the page...");
                return -1;
            }

            ctl00_ContentPlaceHolder1_character_imprisonment5YearsDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_imprisonment12MonthsDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_removalOrderDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_deportedDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_chargedDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_convictedDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_underInvestigationDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_excludedDropDownList.setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_character_removedDropDownList.setSelectedAttribute("No", true);

            workingHolidaySpecific =ctl00_ContentPlaceHolder1_wizardPageHeader_nav_sectionTabs_TabHeaders_ctl03_tabButton.click();
            if(workingHolidaySpecific.asXml().contains("ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList")){
                System.out.println("fill in character succeed!");
                return 1;
            }

            m = messyJS.matcher(workingHolidaySpecific.asXml());
            if (m.find()){
                System.out.println("getting workingHolidaySpecific page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when fill character, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int fillWorkingHolidaySpecific() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList  = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day  = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList  = (HtmlSelect) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList");
            if (ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList  == null) {
                System.out.println("ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList  input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton = (HtmlInput) workingHolidaySpecific.getElementById("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton");
            if (ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton input not found, refresh the page...");
                return -1;
            }


            ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_previousWhsPermitVisaDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_commonWHSQuestions_sufficientFundsHolidayDropDownList .setSelectedAttribute("Yes", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Day .setSelectedAttribute("1", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Month .setSelectedAttribute("8", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_intendedTravelDateDatePicker_Year .setSelectedAttribute("2015", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_beenToNzDropDownList .setSelectedAttribute("No", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_sufficientFundsOnwardTicketDropDownList .setSelectedAttribute("Yes", true);
            ctl00_ContentPlaceHolder1_offshoreDetails_requirementsQuestions_readRequirementsDropDownList .setSelectedAttribute("Yes", true);

            allFormsOK = ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_validateButton.click();
            if(allFormsOK.asXml().contains("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton")){
                System.out.println("fill in Specific succeed!");
                return 1;
            }

            pagestr = allFormsOK.asXml();
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting allFormsOK page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when fill WorkingHolidaySpecific, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int submitForms() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton = (HtmlInput) allFormsOK.getElementById("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton");
            if (ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton input not found, refresh the page...");
                return -1;
            }

            checkpage = ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton.click();
            if(checkpage.asXml().contains("ctl00_ContentPlaceHolder1_falseStatementCheckBox")){
                System.out.println("save all forms succeed!");
                return 1;
            }

            pagestr = checkpage.asXml();
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting allFormsOK page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when submitting forms, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int checkPage() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_falseStatementCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_falseStatementCheckBox");
            if (ctl00_ContentPlaceHolder1_falseStatementCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_falseStatementCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_notesCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_notesCheckBox");
            if (ctl00_ContentPlaceHolder1_notesCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_notesCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_circumstancesCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_circumstancesCheckBox");
            if (ctl00_ContentPlaceHolder1_circumstancesCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_circumstancesCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_warrantsCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_warrantsCheckBox");
            if (ctl00_ContentPlaceHolder1_warrantsCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_warrantsCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_informationCheckBox= (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_informationCheckBox");
            if ( ctl00_ContentPlaceHolder1_informationCheckBox== null) {
                System.out.println("ctl00_ContentPlaceHolder1_informationCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_healthCheckBox= (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_healthCheckBox");
            if ( ctl00_ContentPlaceHolder1_healthCheckBox== null) {
                System.out.println("ctl00_ContentPlaceHolder1_healthCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_adviceCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_adviceCheckBox");
            if (ctl00_ContentPlaceHolder1_adviceCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_adviceCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_registrationCheckBox= (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_registrationCheckBox");
            if (ctl00_ContentPlaceHolder1_registrationCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_registrationCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_entitlementCheckbox= (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_entitlementCheckbox");
            if ( ctl00_ContentPlaceHolder1_entitlementCheckbox== null) {
                System.out.println("ctl00_ContentPlaceHolder1_entitlementCheckbox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_permitExpiryCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_permitExpiryCheckBox");
            if (ctl00_ContentPlaceHolder1_permitExpiryCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_permitExpiryCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox");
            if (ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_submitImageButton = (HtmlInput) checkpage.getElementById("ctl00_ContentPlaceHolder1_submitImageButton");
            if (ctl00_ContentPlaceHolder1_submitImageButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_submitImageButton input not found, refresh the page...");
                return -1;
            }

            ctl00_ContentPlaceHolder1_falseStatementCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_notesCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_circumstancesCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_warrantsCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_informationCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_healthCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_adviceCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_registrationCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_entitlementCheckbox.setChecked(true);
            ctl00_ContentPlaceHolder1_permitExpiryCheckBox.setChecked(true);
            ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox.setChecked(true);

            choosePayWay = ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton.click();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_payLaterBranchImageButton")
                    ||
                    pagestr.contains("ctl00_ContentPlaceHolder1_payLaterImage")
                    ||
                    pagestr.contains("ctl00_ContentPlaceHolder1_payNowImage")
                    ){
                System.out.println("submitting checkPage succeed! the "+ wormUser_.toString() +"'s apply will be locked for 20m! Now get choosePayWay page...");
                return 1;
            }

            pagestr = choosePayWay.asXml();
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting choosePayWay with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }

        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in checkPage, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int choosePayWay() throws Exception{
        try {
            pagestr = choosePayWay.asXml();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_falseStatementCheckBox")){
                ctl00_ContentPlaceHolder1_falseStatementCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_falseStatementCheckBox");
                if (ctl00_ContentPlaceHolder1_falseStatementCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_falseStatementCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_notesCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_notesCheckBox");
                if (ctl00_ContentPlaceHolder1_notesCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_notesCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_circumstancesCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_circumstancesCheckBox");
                if (ctl00_ContentPlaceHolder1_circumstancesCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_circumstancesCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_warrantsCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_warrantsCheckBox");
                if (ctl00_ContentPlaceHolder1_warrantsCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_warrantsCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_informationCheckBox= (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_informationCheckBox");
                if ( ctl00_ContentPlaceHolder1_informationCheckBox== null) {
                    System.out.println("ctl00_ContentPlaceHolder1_informationCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_healthCheckBox= (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_healthCheckBox");
                if ( ctl00_ContentPlaceHolder1_healthCheckBox== null) {
                    System.out.println("ctl00_ContentPlaceHolder1_healthCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_adviceCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_adviceCheckBox");
                if (ctl00_ContentPlaceHolder1_adviceCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_adviceCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_registrationCheckBox= (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_registrationCheckBox");
                if (ctl00_ContentPlaceHolder1_registrationCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_registrationCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_entitlementCheckbox= (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_entitlementCheckbox");
                if ( ctl00_ContentPlaceHolder1_entitlementCheckbox== null) {
                    System.out.println("ctl00_ContentPlaceHolder1_entitlementCheckbox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_permitExpiryCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_permitExpiryCheckBox");
                if (ctl00_ContentPlaceHolder1_permitExpiryCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_permitExpiryCheckBox input not found, refresh the page...");
                    return -1;
                }
                ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox");
                if (ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox input not found, refresh the page...");
                    return -1;
                }

                ctl00_ContentPlaceHolder1_falseStatementCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_notesCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_circumstancesCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_warrantsCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_informationCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_healthCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_adviceCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_registrationCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_entitlementCheckbox.setChecked(true);
                ctl00_ContentPlaceHolder1_permitExpiryCheckBox.setChecked(true);
                ctl00_ContentPlaceHolder1_medicalInsuranceCheckBox.setChecked(true);
            }

            if(pagestr.contains("ctl00_ContentPlaceHolder1_payLaterBranchImageButton")) {
                //pay later at bank
                ctl00_ContentPlaceHolder1_payLaterBranchImageButton = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_payLaterBranchImageButton");
                if (ctl00_ContentPlaceHolder1_payLaterBranchImageButton == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_payLaterBranchImageButton input not found, refresh the page...");
                    return -1;
                }
                applyPage = ctl00_ContentPlaceHolder1_payLaterBranchImageButton.click();
                pagestr = applyPage.asXml();
                if(pagestr.contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink")){
                    System.out.println("choose to pay later at a bank succeed!!! Congratulation!!!");
                    return 2;
                }
                m = messyJS.matcher(pagestr);
                if (m.find()){
                    System.out.println("choose to pay later in a bank with the messy JS page, start anew...");
                    //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                    return -4;
                }
            }

            if(pagestr.contains("ctl00_ContentPlaceHolder1_payLaterImage")) {
                //pay now
                ctl00_ContentPlaceHolder1_payAnchor = (HtmlAnchor) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_payAnchor");
                if (ctl00_ContentPlaceHolder1_payAnchor == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_payAnchor input not found, refresh the page...");
                    return -1;
                }
                pay1 = ctl00_ContentPlaceHolder1_payAnchor.click();
                pagestr = pay1.asXml();
                if(pagestr.contains("ctl00_ContentPlaceHolder1_onlinePaymentAnchor")){
                    System.out.println("choose to pay now, succeed to get the payment page1");
                    return 3;
                }
                m = messyJS.matcher(pagestr);
                if (m.find()){
                    System.out.println("choose to pay later with the messy JS page, start anew...");
                    //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                    return -4;
                }
                return 3;
            }

            if(pagestr.contains("ctl00_ContentPlaceHolder1_submitImageButton")) {
                //submit again
                ctl00_ContentPlaceHolder1_submitImageButton = (HtmlInput) choosePayWay.getElementById("ctl00_ContentPlaceHolder1_submitImageButton");
                if (ctl00_ContentPlaceHolder1_submitImageButton == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_submitImageButton input not found, refresh the page...");
                    return -1;
                }
                choosePayWay = ctl00_ContentPlaceHolder1_submitImageButton.click();
                pagestr = choosePayWay.asXml();
                m = messyJS.matcher(pagestr);
                if (m.find()){
                    System.out.println("choose Pay Way with the messy JS page, start anew...");
                    //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                    return -4;
                }
                System.out.println("checked again, now choose Pay Way...");
                return 4;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out when choosing a pay way, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return -1;
    }

    private int payFromApplyPage() throws Exception{
        try {
            pagestr = applyPage.asXml();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink")){
                ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink = (HtmlAnchor) applyPage.getElementById("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink");
                if (ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink == null) {
                    System.out.println("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton input not found, refresh the page...");
                    return -1;
                }
                pay1 = ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink.click();

                pagestr = pay1.asXml();
                if(pagestr.contains("ctl00_ContentPlaceHolder1_onlinePaymentAnchor")){
                    System.out.println("get pay1 page succeed...");
                    return 1;
                }
                m = messyJS.matcher(pagestr);
                if (m.find()){
                    System.out.println("getting pay1 page with the messy JS page, start anew...");
                    //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                    return -4;
                }

            }else{
                //restart apply page
                applyPage = webclient.getPage("https://www.immigration.govt.nz/workingholiday/");
                pagestr = applyPage.asXml();
                if(pagestr.contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink")) {
                    ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink = (HtmlAnchor) applyPage.getElementById("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink");
                    if (ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink == null) {
                        System.out.println("ctl00_ContentPlaceHolder1_wizardPageFooter_wizardPageNavigator_submitImageButton input not found, refresh the page...");
                        return -1;
                    }
                    pay1 = ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_payHyperLink.click();
                    pagestr = pay1.asXml();
                    if (pagestr.contains("ctl00_ContentPlaceHolder1_onlinePaymentAnchor")) {
                        System.out.println("get pay1 page succeed...");
                        return 1;
                    }
                    m = messyJS.matcher(pagestr);
                    if (m.find()) {
                        System.out.println("getting pay1 page with the messy JS page, start anew...");
                        //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                        return -4;
                    }

                }else{
                    //still not ? return pay button missing code, should edit apply again.
                    System.out.println("pay button is missing, edit apply again...");
                    return -7;
                }
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in applyPage, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int pay1() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_onlinePaymentAnchor = (HtmlAnchor) pay1.getElementById("ctl00_ContentPlaceHolder1_onlinePaymentAnchor");
            if (ctl00_ContentPlaceHolder1_onlinePaymentAnchor == null) {
                System.out.println("ctl00_ContentPlaceHolder1_onlinePaymentAnchor input not found, refresh the page...");
//                System.out.println("dyyr test pay1 page:\n"+pay1.asXml());
                return -1;
            }

            pay2 = ctl00_ContentPlaceHolder1_onlinePaymentAnchor.click();
            System.out.println("pay2URL: "+pay2.getUrl());
            pagestr = pay2.asXml();
            if(pagestr.contains("ctl00_ContentPlaceHolder1_payorNameTextBox")){
                System.out.println("payPage1 click succeed");
                return 1;
            }
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting pay2 page with the messy JS page, submit pay1 again...");
//                return -4;
                return 1;
                //dyyr test if I do not mock pay2
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in pay1 page, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int pay2() throws Exception{
        try {
            ctl00_ContentPlaceHolder1_payorNameTextBox = (HtmlInput) pay2.getElementById("ctl00_ContentPlaceHolder1_payorNameTextBox");
            if (ctl00_ContentPlaceHolder1_payorNameTextBox == null) {
                System.out.println("ctl00_ContentPlaceHolder1_payorNameTextBox input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_okImageButton = (HtmlInput) pay2.getElementById("ctl00_ContentPlaceHolder1_okImageButton");
            if (ctl00_ContentPlaceHolder1_okImageButton == null) {
                System.out.println("ctl00_ContentPlaceHolder1_okImageButton input not found, refresh the page...");
                return -1;
            }
            ctl00_ContentPlaceHolder1_payorNameTextBox.setValueAttribute("Jim");
            pay3 = ctl00_ContentPlaceHolder1_okImageButton.click();
            pagestr = pay3.asXml();
            if(pagestr.contains("card_type_VISA")){
                System.out.println("payPage2 click succeed");
                return 1;
            }
            System.out.println("dyyr test pay3 page: " + pay3.asXml());
            System.out.println("dyyr test pay3 url: " + pay3.getUrl());
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting pay3 page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }

        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in pay2 page, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int pay3() throws Exception{
        try {
            card_type_VISA = (HtmlInput) pay3.getElementById("card_type_VISA");
            if (card_type_VISA == null) {
                System.out.println("card_type_VISA input not found, refresh the page...");
                return -1;
            }

            pay4 = card_type_VISA.click();
            pagestr = pay4.asXml();
            m = messyJS.matcher(pagestr);
            if (m.find()){
                System.out.println("getting pay4 page with the messy JS page, start anew...");
                //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                return -4;
            }
            if(pagestr.contains("card_type_VISA")){
                System.out.println("payPage3 click succeed, and have chosen VISA card");
                return 1;
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in pay3 page, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }

            throw e;
        }
        return 1;
    }

    private int pay4() throws Exception{
        try {
            cardnumber  = (HtmlInput) pay4.getElementById("cardnumber");
            if (cardnumber  == null) {
                System.out.println("cardnumber input not found, refresh the page...");
                return -1;
            }
            cardverificationcode   = (HtmlInput) pay4.getElementById("cardverificationcode");
            if (cardverificationcode   == null) {
                System.out.println("cardverificationcode input not found, refresh the page...");
                return -1;
            }
            expirymonth  = (HtmlInput) pay4.getElementById("expirymonth");
            if (expirymonth  == null) {
                System.out.println("expirymonth input not found, refresh the page...");
                return -1;
            }
            expiryyear  = (HtmlInput) pay4.getElementById("expiryyear");
            if (expiryyear  == null) {
                System.out.println("expiryyear input not found, refresh the page...");
                return -1;
            }
            cardholder  = (HtmlInput) pay4.getElementById("cardholder");
            if (cardholder  == null) {
                System.out.println("cardholder input not found, refresh the page...");
                return -1;
            }
            pay_button  = (HtmlInput) pay4.getElementById("pay_button");
            if (pay_button  == null) {
                System.out.println("pay_button input not found, refresh the page...");
                return -1;
            }
            pay5 = pay_button.click();
            okResult = Pattern.compile("</script>\\W*</apm_do_not_touch>\\W*</body>\\W*</html>");
            for(int i=0; i<=60; i++){
                if (pay5.asXml().contains("IS COMPLETE")
                        ||pay5.asXml().contains("is Complete")
                        ||pay5.asXml().contains("IS SUCCEED")
                        ||pay5.asXml().contains("is Succeed")
                        ||pay5.asXml().contains("is succeed")
                        ||pay5.asXml().contains("is complete")
                        ){
                    System.out.println("paying succeed!");
                    System.out.println("Hello, New Zealand~~~~~~~~~~~~~~~~~~~~");
                    return 5;
                }
                //此处需加入支付结果失败判断 dyyr
                m = messyJS.matcher(pay5.asXml());
                if (m.find()){
                    System.out.println("getting pay5 page with the messy JS page, start anew...");
                    //如果确认乱码JS是jstimeout造成，则此处理可改为continue；所以start anew描述换掉，报错号可保留。dyyr
                    return -4;
                }
                webclient.waitForBackgroundJavaScript(1000);
            }
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in pay5 page, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int isSuccessd() throws Exception{
        try {
            applyPage = webclient.getPage("https://www.immigration.govt.nz/workingholiday/");
            if ( applyPage.asXml().contains("Access denied") ) {
                System.out.println("login session missing when 'getApplyPage', login again...");
                return -2;
            }

            if (applyPage.asXml().contains("ctl00_ContentPlaceHolder1_applicationList_applicationsDataGrid_ctl02_editHyperLink")) {
                System.out.println("apply not finished");
                return -8;
            }

            if (applyPage.asXml().contains("ctl00_ContentPlaceHolder1_applyNowButton")
                    || applyPage.asXml().contains("ctl00_ContentPlaceHolder1_countryDropDownList")) {
                System.out.println("there is no earlier apply");
                return -3;
            }
            System.out.println("Apply succeed!!!");
            System.out.println("Hello, New Zealand~~~~~~~~~~~~~~~~~~~~");
        }
        catch (Exception e){
            if (e instanceof HttpHostConnectException||e instanceof SocketTimeoutException||e instanceof UnknownHostException|| e instanceof FailingHttpStatusCodeException|| e instanceof ConnectTimeoutException){
                //需重新处理timeout异常 dyyr
                System.out.println("connection time out in test apply page, reload...");
                return 0;
            }
            if (e instanceof ScriptException){
                System.out.println("ignore ScriptException...");
            }
            if (e instanceof EvaluatorException){
                System.out.println("ignore EvaluatorException...");
            }
            throw e;
        }
        return 1;
    }

    private int myLogin() throws Exception{
        int r1;
        while(true) {
            if (getLoginPage() == 1) {
                for (int i = 0; i <= 60; i++) {
                    r1 = login("dudeea", "Dd123456");
                    if (r1 == 1) {
                        System.out.println("dudeea login succeed");
                        return 1;
                    }
                    webclient.waitForBackgroundJavaScript(1000);
                }
            }
        }
    }

    private int fillInforms() throws Exception{
        int r1;
        while(true) {
            r1 = fillPersonal1();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -1;
            }
        }
        while(true) {
            r1 = fillPersonal2();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = fillMedical1();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = fillCharacter();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = fillWorkingHolidaySpecific();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        return 1;
    }

    private int myPay() throws Exception{
        int r1;
        while(true) {
            r1 = choosePayWay();
            if(r1 == 2){
                return 2;
            }
            if(r1 == 3){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0||r1==4) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
//        while(true) {
//            r1 = payFromApplyPage();
//            if(r1 == 1){
//                break;
//            }
//            if (r1 == -1) {
//                webclient.waitForBackgroundJavaScript(1000);
//                continue;
//            }
//            if (r1 == 0) {
//                continue;
//            }
//            if (r1 == -4) {
//                return -4;
//            }
//        }
        while(true) {
            r1 = pay1();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                continue;
            }
        }
        while(true) {
            r1 = pay2();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                webclient.waitForBackgroundJavaScript(1000);
                continue;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = pay3();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return  -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = pay4();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
            if (r1 == 5) {
                return 5;
            }
        }
        return 1;
    }

    private int myPayFromApplyPage() throws Exception{
        int r1;
        while(true) {
            r1 = payFromApplyPage();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                webclient.waitForBackgroundJavaScript(1000);
                continue;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;//should be continue?
            }
            if (r1 == -7) {
                return -7;//should be continue?
            }
        }
        while(true) {
            r1 = pay1();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                continue;
            }
        }
        while(true) {
            r1 = pay2();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                webclient.waitForBackgroundJavaScript(1000);
                continue;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = pay3();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return  -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
        }
        while(true) {
            r1 = pay4();
            if(r1 == 1){
                break;
            }
            if (r1 == -1) {
                return -1;
            }
            if (r1 == 0) {
                continue;
            }
            if (r1 == -4) {
                return -4;
            }
            if (r1 == 5) {
                return 5;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws Exception {

        long begintime = System.currentTimeMillis();
        if(args.length>0) {
            root = args[0];
        }
//        loadUserFields();
        System.out.println("感谢周旭同学~~~~~o(*≧▽≦)ツ");
        messyJS = Pattern.compile("</script>\\W*</apm_do_not_touch>\\W*</body>\\W*</html>");
//        String applicationName = "Mozilla";
//        String applicationVersion = "5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36";
//        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36";
//        int browserVersionNumeric = 17;
//        BrowserVersion browserVersion = new BrowserVersion(applicationName,applicationVersion,userAgent,browserVersionNumeric);
        webclient = new WebClient(BrowserVersion.CHROME);
        webclient.getOptions().setCssEnabled(false);
        webclient.getOptions().setJavaScriptEnabled(true);
        webclient.getOptions().setThrowExceptionOnScriptError(false);
        webclient.getOptions().setTimeout(20000);
        webclient.setJavaScriptTimeout(20000);
        webclient.getOptions().setUseInsecureSSL(true);
        webclient.getOptions().setRedirectEnabled(true);
        webclient.setAjaxController(new NicelyResynchronizingAjaxController());
        cm = webclient.getCookieManager();
        cm.setCookiesEnabled(true);
//        webclient.getOptions().setActiveXNative(true);
//        webclient.getOptions().setAppletEnabled(true);
        try {
            webclient.getCurrentWindow().getEnclosedPage();
            Worm myworm = new Worm();
            myworm.myLogin();
            myworm.getApplyPage();
            applynowL:
            while(true) {
                int rApplyNow = myworm.applynow();
                if (rApplyNow==1){
                    //do following;
                }
                if (rApplyNow==-1){
                    continue ;
                }
                if (rApplyNow==0||rApplyNow==-4){
                    continue;
                }
                if (rApplyNow==-5){
                    editApplyL:
                    for (int i = 0; i <= 60; i++) {
                        int rEdit = myworm.editApply();
                        if(rEdit == -4)
                            return;
                        if (rEdit == 1) {
                            break editApplyL;
                        }
                        if(rEdit==9){
                            if(myworm.myPayFromApplyPage()==5) {
                                return ;
                            }
                        }
                        webclient.waitForBackgroundJavaScript(1000);
                        if (i == 60){
                            System.out.println("failed to get forms!");
                            return;
                        }
                    }
                }
                int rFill = myworm.fillInforms();
                if(rFill == -4){
                    return;
                }
                if(rFill == -1){
                    continue ;
                }
                if(rFill == 1){
                    break applynowL ;
                }
            }


            myworm.submitForms();
            myworm.checkPage(); //maybe需循环处理 dyyr
            myworm.myPay();
//            System.out.println("dyyr test pay result: " + pay5.asXml());
            myworm.isSuccessd();
            }
        finally {
//            webclient.closeAllWindows();
            System.out.print("cost time: " + (System.currentTimeMillis() - begintime) / 1000 + "s");

           Thread.sleep(1000*60*60*6);
        }
    }


}




