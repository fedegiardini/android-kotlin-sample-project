package com.kotlinsampleapp.mainfeature.model

import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class Shipping(
    val free_shipping: Boolean
)