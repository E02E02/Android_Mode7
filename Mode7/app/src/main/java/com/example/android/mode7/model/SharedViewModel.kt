package com.example.android.mode7.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SharedViewModel : ViewModel() {

    private val _selectedmapnumber = MutableLiveData<Int>(0)
    val selectedmapnumber: LiveData<Int>
        get() = _selectedmapnumber

    fun setSelectedMapNumber(numberSelectedMap: Int) {
        _selectedmapnumber.value = numberSelectedMap
    }

}