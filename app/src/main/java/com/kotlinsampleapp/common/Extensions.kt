package com.kotlinsampleapp.common

import android.view.View

/**
 * File that contains Kotlin Extension functions
 *
 * @author federico.giardini
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}