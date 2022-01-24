package com.stock.market.utils

const val BASE_URL_TEST: String = "https://api-sandbox.invertironline.com"
const val BASE_URL_PROD: String = "https://api.invertironline.com"

const val USER_NAME_PRD = "juanmaggi266"
const val PASSWORD_PRD = "juan8420"

const val USER_NAME_TEST = "juanmaggitest"
const val PASSWORD_TEST = "juanmaggitest"

const val DATABASE_NAME = "base_project_db"
const val SHARED_PREFERENCE: String = "base_project_sp"
const val DEFAULT_INT_VALUE = -1
const val EMPTY_STRING = ""
const val EMPTY_SHARE_PRICE = "0.00"

fun getBaseURL(): String {
    return BASE_URL_PROD
}

fun getUserName() : String {
    return USER_NAME_PRD
}

fun getPassword(): String {
    return PASSWORD_PRD
}

