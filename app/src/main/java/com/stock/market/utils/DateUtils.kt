package com.stock.market.utils

import android.content.Context
import android.text.TextUtils
import com.stock.market.R
import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_IOL_API = "yyyy-MM-dd'T'HH:mm:ss.SSS"
const val FORMAT_IOL_API_2 = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_PATTERN = "HH:mm:ss"
const val FORMAT_ONLY_MONTH_AND_YEAR = "dd MMMM yyyy"
const val FORMAT_DAY_MONTH = "dd MMM"
const val FORMAT_TIME = "HH:mm"
const val FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd"
const val MARKET_TIME_CLOSE = "17:00:00"
const val MARKET_TIME_OPEN = "11:00:00"

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    return format.format(date)
}

fun convertLongToDate(time: Long, formatDateToShow: String): String {
    val dateString = SimpleDateFormat(formatDateToShow).format(Date(time))
    return dateString
    //FORMAT_ONLY_MONTH_AND_YEAR
}

fun getDate(apiDate: String, formatApiDate: String, formatDateToShow: String): String {
    var result = EMPTY_STRING
    if (isValidApiDate(apiDate)) {
        val sdf = SimpleDateFormat(formatApiDate, getLocaleDefault())
        val formatter = SimpleDateFormat(formatDateToShow, getLocaleDefault())
        try {
            val dateCreated = sdf.parse(apiDate)
            result = formatter.format(dateCreated)
        } catch (e: Exception) {
            //DBLog.e(LOG_TAG, e)
        }

    }
    return result
}

fun isMarketOpen(context: Context): Boolean {
    val sdf = SimpleDateFormat(FORMAT_IOL_API_2)
    val cal = Calendar.getInstance()
    val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
    val isWeekend = (dayOfWeek == 1 || dayOfWeek == 7)
    val isHoliday = false //Holiday table is neccesary to do this
    val currentTime = cal.time
    val strCurrentTime = sdf.format(currentTime)
    return  (sdf.parse(strCurrentTime) > getMarketOpenTime(context)) &&
            (sdf.parse(strCurrentTime) < getMarketCloseTime(context)) && !(isWeekend) && !isHoliday
}

fun getMarketCloseTime(context: Context) : Date {
    val sdf = SimpleDateFormat(FORMAT_IOL_API_2)
    val result = String.format(context.getString(R.string.date_time), getCurrentDate(), MARKET_TIME_CLOSE)
    return sdf.parse(result)
}

fun getMarketOpenTime(context: Context) : Date {
    val sdf = SimpleDateFormat(FORMAT_IOL_API_2)
    val result = String.format(context.getString(R.string.date_time), getCurrentDate(), MARKET_TIME_OPEN)
    return sdf.parse(result)
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat(FORMAT_YEAR_MONTH_DAY)
    return sdf.format(Calendar.getInstance().time)
}

fun getDateSubstractDays(daysToSubstract: Int): String {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat(FORMAT_YEAR_MONTH_DAY)
    cal.add(Calendar.DAY_OF_MONTH, daysToSubstract)
    return sdf.format(cal.time)
}

private fun getLocaleDefault(): Locale {
    return Locale.ROOT
}

private fun isValidApiDate(apiDate: String): Boolean {
    return !TextUtils.isEmpty(apiDate) && apiDate.toUpperCase() != "NONE"
}

