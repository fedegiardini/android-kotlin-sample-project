package com.kotlinsampleapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class Shipping(
    @Json(name = "free_shipping")
    val freeShipping: Boolean
)