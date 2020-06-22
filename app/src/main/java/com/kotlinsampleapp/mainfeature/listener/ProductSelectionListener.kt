package com.kotlinsampleapp.mainfeature.listener

import com.kotlinsampleapp.mainfeature.model.ProductViewData

/**
 * Listener used for navigation when clicking on a Product
 *
 * @author federico.giardini
 */
interface ProductSelectionListener {
    fun onProductSelected(product: ProductViewData)
}