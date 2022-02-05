package com.stock.market.utils

const val BASE_URL_TEST: String = "https://api-sandbox.invertironline.com"
const val BASE_URL_PROD: String = "https://api.invertironline.com"

const val URL_NEWS: String = "https://api.marketaux.com/v1/news/all"
const val TOKEN_URL_NEWS: String = "cbBSdXyjNflrAUE06GDN0mBFEX87UTundRwQqAW4"

const val USER_NAME_PRD = "juanmaggi266"
const val PASSWORD_PRD = "juan8420"


const val EMPTY_STRING = ""

fun getBaseURL(): String {
    return BASE_URL_PROD
}

fun getUserName() : String {
    return USER_NAME_PRD
}

fun getPassword(): String {
    return PASSWORD_PRD
}

