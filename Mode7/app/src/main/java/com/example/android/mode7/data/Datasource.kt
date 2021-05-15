package com.example.android.mode7.data

import com.example.android.mode7.R
import com.example.android.mode7.model.BackgroundMap

/**
 * [Datasource] generates a list of [Map]
 */
class Datasource() {
    fun loadMaps(): List<BackgroundMap> {
        return listOf<BackgroundMap>(
                BackgroundMap(R.string.map1, R.drawable.map1),
                BackgroundMap(R.string.map2, R.drawable.map2),
                BackgroundMap(R.string.map3, R.drawable.map3),
                BackgroundMap(R.string.map4, R.drawable.map4),
                BackgroundMap(R.string.map5, R.drawable.map5),
                BackgroundMap(R.string.map6, R.drawable.map6),
                BackgroundMap(R.string.map7, R.drawable.map7))
    }
}

