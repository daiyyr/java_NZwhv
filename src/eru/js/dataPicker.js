// create array of month names
var allMonths = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
var allMonthsLong = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

//script level variables
var today = new Date;
var todayDay = today.getDate();
var todayMonth = today.getMonth() + 1;
var todayYear = today.getFullYear();
var maxDateDay;
var maxDateMonth;
var maxDateYear;
var minDateDay;
var minDateMonth;
var minDateYear;
var showBlank;
var yearFormat, monthFormat, dayFormat;

function initVars(bShowBlank, dtmMin, dtmMax, intDayFormat, intMonthFormat, intYearFormat){

    var max = new Date(dtmMax.value);
    maxDateDay = max.getDate();
    maxDateMonth = max.getMonth() + 1;
    maxDateYear = max.getFullYear();

    var min = new Date(dtmMin.value);
    minDateDay = min.getDate();
    minDateMonth = min.getMonth() + 1;
    minDateYear = min.getFullYear();

    showBlank = bShowBlank;
    yearFormat=intYearFormat
    monthFormat=intMonthFormat
    dayFormat=intDayFormat

}

// returns number of days in particular month of particular year
function daysInMonth(month, year) {
    var maxday;
    var nMonth = parseInt(month);
    var nYear = parseInt(year);
    document
    switch (nMonth) {
        case 2: // test for leapyear
            if (nYear % 400 == 0) {
                maxday = 29;
            } else if (nYear % 100 == 0) {
                maxday = 28;
            } else if (nYear % 4 == 0) {
                maxday = 29;
            } else {
                maxday = 28;
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            maxday = 30;
            break;
        default:
            maxday = 31;
    }
    return maxday;
}

// get the currently selected value from a select list
// required for netscape, which doesn't use 'selectObj.value' for select lists
function getSelectValue(selectObj) {
    var selectedIndex = selectObj.options.selectedIndex;
    if (selectedIndex < 0 || selectedIndex >= selectObj.options.length) return null;
    return selectObj.options[selectedIndex].value
}

// get the currently selected value from a select list
// required for netscape, which doesn't use 'selectObj.value' for select lists
function setSelectValue(selectObj, newValue) {
    for (var index = 0; index < selectObj.options.length; index++) {
        if (selectObj.options[index].value == newValue) {
            selectObj.options.selectedIndex = index;
            return;
        }
    }

    selectObj.selectedIndex = -1;
}

// rebuild day select list
// requires yearObj and monthObj to contain a valid year and month respectively
function rebuildDays(dayObj, monthObj, yearObj) {
    var yearValue = getSelectValue(yearObj);
    var monthValue = getSelectValue(monthObj);

    var minday = 1;
    if (yearValue == minDateYear && monthValue == minDateMonth) minday = minDateDay;

    var maxday = daysInMonth(monthValue, yearValue);
    if (yearValue == maxDateYear && monthValue == maxDateMonth) maxday = maxDateDay;

    var oldDay = getSelectValue(dayObj);
    if (oldDay == null || isNaN(oldDay)) oldDay = minday;

    oldDay = parseInt(oldDay);
    if (oldDay < minday && oldDay!=0) oldDay = minday;
    if (oldDay > maxday ) oldDay = maxday;

    var oldLength = dayObj.options.length;
    dayObj.options.length = 0;
    var i, n, j;
    if (showBlank) {
        dayObj.options[0] = new Option('---', 0);
        j = 1;
    } else {
        j = 0;
    }

    var day;
    for (i = j, n = minday; n <= maxday; n++, i++) {
        day = n;
        if (dayFormat == 2) {
            if (n < 10) {day = "0" + n} ;
        }
        dayObj.options[i] = new Option(day, n);
    }
    setSelectValue(dayObj, oldDay);
    //if (oldDay == 0) {
//	setSelectValue(monthObj, 0);
//	setSelectValue(yearObj, 0);
//  }

    if (dayObj.options.length > oldLength && navigator.appName == 'Netscape' && navigator.platform == 'MacPPC')
        setTimeout(hackForMac, 100, dayObj);
}

// rebuild month (and day) select list
// requires yearObj to be a valid year
function rebuildMonths(dayObj, monthObj, yearObj) {

    var yearValue = getSelectValue(yearObj);


    var minmonth = 1;

    if (yearValue == minDateYear) minmonth = minDateMonth;


    var maxmonth = 12;

    if (yearValue == maxDateYear) maxmonth = maxDateMonth;


    var oldMonth = getSelectValue(monthObj);

    if (oldMonth == null || isNaN(oldMonth)) oldMonth = minmonth;


    oldMonth = parseInt(oldMonth);

    if (oldMonth < minmonth && oldMonth!=0) oldMonth = minmonth;
    if (oldMonth > maxmonth) oldMonth = maxmonth;


    var oldLength = monthObj.options.length;

    monthObj.options.length = 0;

    var i, n, j;
    if (showBlank) {
        monthObj.options[0] = new Option('---', 0);
        j = 1;
    } else {
        j = 0;
    }

    for (i = j, n = minmonth; n <= maxmonth; n++, i++) {
        if (monthFormat == 1) {
            monthObj.options[i] = new Option(allMonths[n-1], n);
        } else {
            monthObj.options[i] = new Option(allMonthsLong[n-1], n);
        }
    }

    setSelectValue(monthObj, oldMonth);
    //if (oldMonth == 0) {
    //setSelectValue(dayObj, 0);
//	setSelectValue(yearObj, 0);
//  }

    if (monthObj.options.length > oldLength && navigator.appName == 'Netscape' && navigator.platform == 'MacPPC')

        setTimeout(hackForMac, 100, monthObj);


    // rebuild day select list too

    rebuildDays(dayObj, monthObj, yearObj);

}


// rebuild year (and month and day) select list
function rebuildYears(dayObj, monthObj, yearObj) {

    var minyear = minDateYear;
    var maxyear = maxDateYear;
    var oldYear = getSelectValue(yearObj);

    if (oldYear == null || isNaN(oldYear)) oldYear = minyear;
    oldYear = parseInt(oldYear);

    if (oldYear < minyear && oldYear!=0) oldYear = minyear;
    if (oldYear > maxyear) oldYear = maxyear;

    var oldLength = yearObj.options.length;

    yearObj.options.length = 0;

    var i, n, j;
    if (showBlank) {
        yearObj.options[0] = new Option('---', 0);
        j = 1;
    } else {
        j = 0;
    }

    var year ;
    for (i = j, n = minyear; n <= maxyear; n++, i++) {
        year = String(n);
        if (yearFormat == 1) { year = year.substr(year.length-2,2) }
        yearObj.options[i] = new Option(year, n);
    }

    setSelectValue(yearObj, oldYear);
    //if (oldYear == 0) {
//	setSelectValue(dayObj, 0);
//	setSelectValue(monthObj, 0);
    //}

    if (yearObj.options.length > oldLength && navigator.appName == 'Netscape' && navigator.platform == 'MacPPC')
        setTimeout(hackForMac, 100, yearObj);


    // rebuild month and day select lists too
    rebuildMonths(dayObj, monthObj, yearObj);

}

// called by a setTimeout to refresh one of the dynamic select-lists

// required for Mac running netscape navigator when list length increases

// and selected item number is bigger than the previous length

function hackForMac(pObj) {

    pObj.selectedIndex += 0;

}

// Returns the current date
function getDate(dayObj, monthObj, yearObj) {

    var day = getSelectValue(dayObj);
    var month = getSelectValue(monthObj);
    var year = getSelectValue(yearObj);

    if (day == 0 || month == 0 || year == 0)
    {
        return '';
    }
    else
    {
        return year + "-" + month + "-" + day;
    }
}

// sets date to today
function setToday(dayObj, monthObj, yearObj) {

    rebuildYears(dayObj, monthObj, yearObj);

    setSelectValue(yearObj, todayYear);


    rebuildMonths(dayObj, monthObj, yearObj);

    setSelectValue(monthObj, todayMonth);


    rebuildDays(dayObj, monthObj, yearObj);

    setSelectValue(dayObj, todayDay);


}


// sets date to date specified by param values

function setDateItem(dayObj, monthObj, yearObj, dayValue, monthValue, yearValue) {

    rebuildYears(dayObj, monthObj, yearObj);

    setSelectValue(yearObj,yearValue);


    rebuildMonths(dayObj, monthObj, yearObj);

    setSelectValue(monthObj, monthValue);


    rebuildDays(dayObj, monthObj, yearObj);

    setSelectValue(dayObj, dayValue);

}



// compare two years, return true if the second is later than (or the same as) the first

function compareDates(dayA, monthA, yearA, dayB, monthB, yearB) {

    dayA = parseInt(dayA);

    monthA = parseInt(monthA);

    yearA = parseInt(yearA);

    dayB = parseInt(dayB);

    monthB = parseInt(monthB);

    yearB = parseInt(yearB);


    // compare year figures

    if (yearA < yearB) return true;

    if (yearA > yearB) return false;


    // compare month figures

    if (monthA < monthB) return true;

    if (monthA > monthB) return false;


    // compare day figures

    if (dayA < dayB) return true;

    if (dayA > dayB) return false;


    return true;	// if dates are equal

}

function openCalendar(CalendarURL, datePickerName, currentDate)
{

    var params;
    params = "width=250, height=250, left=40, top=40, resizable=no, menubar=false";
    window.open(CalendarURL + "?control=" + datePickerName + '&currentDate=' + currentDate, '_blank', params);

}
