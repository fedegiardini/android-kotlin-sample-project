package com.kotlinsampleapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class PagingData(
    val total: Int,
    val offset: Int,
    val limit: Int,
    @Json(name = "primary_results")
    val primaryResults: Int
)