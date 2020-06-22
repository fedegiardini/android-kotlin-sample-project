package com.kotlinsampleapp.mainfeature.model

import com.squareup.moshi.JsonClass

/**
 * @author federico.giardini
 */
@JsonClass(generateAdapter = true)
data class PagingData(
    val total: Int,
    val offset: Int,
    val limit: Int,
    val primary_results: Int
)