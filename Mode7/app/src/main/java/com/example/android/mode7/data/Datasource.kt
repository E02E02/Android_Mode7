package com.example.android.mode7.data

import com.example.android.mode7.R

/**
 * [Datasource] generates a list of [Map]
 */
class Datasource() {
    fun loadMaps(): List<Int> {
        return listOf<Int>(
            R.drawable.map1,
            R.drawable.map2,
            R.drawable.map3,
            R.drawable.map4,
            R.drawable.map5,
            R.drawable.map6,
            R.drawable.map7
        )
    }
}

