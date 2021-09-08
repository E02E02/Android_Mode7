package com.example.android.mode7.data

import com.example.android.mode7.R
import com.example.android.mode7.model.Background

/**
 * [Datasource] generates a list of [Map]
 */
class Datasource() {
    fun loadMaps(): List<Background> {
        return listOf<Background>(
            Background(R.string.map1,R.drawable.map1),
            Background(R.string.map2,R.drawable.map2),
            Background(R.string.map3,R.drawable.map3),
            Background(R.string.map4,R.drawable.map4),
            Background(R.string.map5,R.drawable.map5),
            Background(R.string.map6,R.drawable.map6),
            Background(R.string.map7,R.drawable.map7),
        )
    }
}

