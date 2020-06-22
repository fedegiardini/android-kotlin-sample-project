package com.kotlinsampleapp.mainfeature.model

import com.kotlinsampleapp.model.Product

object ProductMapper {
    fun toProductViewData(product: Product): ProductViewData = ProductViewData(
        productId = product.id,
        title = product.title,
        thumbnailUrl = product.thumbnail,
        price = product.price,
        hasFreeShipping = product.shipping.freeShipping
    )
}