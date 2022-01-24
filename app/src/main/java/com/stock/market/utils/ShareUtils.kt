package com.stock.market.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.stock.market.*
import com.stock.market.domain.model.SelectableItem
import com.stock.market.domain.model.Share

val BANKS: Array<String> = arrayOf(BANCO_GALICIA, BANCO_SUPERVIELLE, BANCO_MACRO, BANCO_BBVA)
val ENERGY: Array<String> = arrayOf(PAMPA_ENERGIA, GAS_DEL_SUR, GAS_DEL_NORTE, TRANSENER, CENTRAL_PUERTO)
val INDUSTRIES: Array<String> = arrayOf(ALUAR, TENARYS, PETROLEO_BRASILERO, TERNIUM)
val LOW_PRICES: Array<String> = arrayOf(ALUAR, CENTRAL_PUERTO, TRANSENER, TERNIUM, COMERCIAL_DEL_PLATA, EDENOR, CRESUD, PAMPA_ENERGIA)
val TOP4: Array<String> = arrayOf(BANCO_SUPERVIELLE, TERNIUM, CENTRAL_PUERTO, PAMPA_ENERGIA)

lateinit var shares: List<SelectableItem>

fun getVariationPercentageText(variationPercentage: Float): String {
    var result = EMPTY_STRING
    if (variationPercentage > 0) {
        result = "(+" + variationPercentage.toString() + " %)"
    } else if (variationPercentage < 0) {
        result = "(" + variationPercentage.toString() + " %)"
    } else {
        result = "(= 0.00 %)"
    }
    return result
}

fun getVariationMoneyText(variationPercentage: Float, lastPrice: Float): String {
    var result = EMPTY_STRING
    val variationMoney = String.format("%.2f", lastPrice * variationPercentage / 100)
    if (variationPercentage > 0) {
      result = "+" + variationMoney
    } else if (variationPercentage < 0) {
        result = variationMoney
    } else {
        result = "= 0.00"
    }
    return result
}


fun getVariationColor(variation: Float): Int {
    var result = R.color.colorPrimaryDark
    if (variation > 0) {
        result = R.color.apple_variation
    } else {
        if (variation < 0) {
            result = R.color.alizarin_crimson
        }
    }
    return result
}

fun getVariationBackground(variation: Float, context: Context): Drawable {
    var result = ContextCompat.getDrawable(
        context,R.drawable.share_square_price_variation_neutral)
    if (variation > 0) {
        result = ContextCompat.getDrawable(
            context,R.drawable.shape_square_price_variation_pos)
    } else if (variation < 0) {
        result = ContextCompat.getDrawable(
            context,R.drawable.shape_square_price_variation_neg)
    }
    return result!!
}

fun isBank(shareSymbol: String): Boolean {
    return shareSymbol in BANKS
}

fun isEnergy(shareSymbol: String): Boolean {
    return shareSymbol in ENERGY
}

fun isIndustry(shareSymbol: String): Boolean {
    return shareSymbol in INDUSTRIES
}

fun isLowPrice(shareSymbol: String): Boolean {
    return shareSymbol in LOW_PRICES
}

fun isTop4(shareSymbol: String): Boolean {
    return shareSymbol in TOP4
}

fun setShares(shareList: Array<Share>) {
    var result = mutableListOf<SelectableItem>()
    var i = 1
    for (share in shareList) {
        var selectableItem = SelectableItem()
        selectableItem.id = i
        selectableItem.name = share.symbol
        result.add(selectableItem)
        i++
    }
    shares = result
}