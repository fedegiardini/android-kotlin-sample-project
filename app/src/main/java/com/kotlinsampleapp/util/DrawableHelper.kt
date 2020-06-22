package com.kotlinsampleapp.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.kotlinsampleapp.R

/**
 * @author federico.giardini
 */
object DrawableHelper {
    fun getCircularDrawable(context: Context): CircularProgressDrawable =
        CircularProgressDrawable(context).apply {
            strokeWidth = 1f
            centerRadius = 5f
            backgroundColor = R.color.colorAccent
            start()
        }
}