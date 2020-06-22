package com.kotlinsampleapp.mainfeature.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class Seller(
    val id: String,
    @Json(name = "permalink")
    val profileLink: String?,
    @Json(name = "power_seller_status")
    val powerSellerStatus: String?,
    @Json(name = "car_dealer")
    val isCarDealer: Boolean,
    @Json(name = "real_estate_agency")
    val isRealEstateAgency: Boolean
)