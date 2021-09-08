package com.example.android.mode7.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * [Background] is the data class to represent the Background text and imageResourceId
 */
data class Background(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)