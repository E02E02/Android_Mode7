package com.example.android.mode7.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [Map] is the data class to store the name of a map and its corresponding imageResourceId
 */
data class BackgroundMap(
        @StringRes val stringResourceId: Int,
        @DrawableRes val imageResourceId: Int
)
