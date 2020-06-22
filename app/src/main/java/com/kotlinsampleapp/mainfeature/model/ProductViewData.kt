package com.kotlinsampleapp.mainfeature.model

data class ProductViewData(
    val productId: String,
    val title: String,
    val thumbnailUrl: String,
    val price: Float,
    val hasFreeShipping: Boolean
)