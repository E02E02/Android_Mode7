package com.example.android.mode7.model

import androidx.lifecycle.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SharedViewModel : ViewModel() {

    fun setSelectedMapNumber(numberSelectedMap: Int) {
        _selectedmapnumber.value = numberSelectedMap
    }

    private val _selectedmapnumber = MutableLiveData<Int>(0)
    val selectedmapnumber: LiveData<Int> = _selectedmapnumber
}