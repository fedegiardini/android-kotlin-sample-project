package com.kotlinsampleapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "site_id")
    val siteId: String,
    val query: String,
    val paging: PagingData,
    val results: List<Product>
)