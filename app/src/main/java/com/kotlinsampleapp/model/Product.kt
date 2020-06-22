package com.kotlinsampleapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class Product(
    val id: String,
    @Json(name = "site_id")
    val siteId: String,
    val title: String,
    val seller: Seller,
    val price: Float,
    @Json(name = "currency_id")
    val currencyId: String,
    @Json(name = "available_quantity")
    val availableQuantity: Int,
    @Json(name = "sold_quantity")
    val soldQuantity: Int,
    val thumbnail: String,
    val shipping: Shipping
)