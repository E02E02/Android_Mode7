package com.example.android.mode7.model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SharedViewModel : ViewModel() {

    private val _selectedmapnumber = MutableLiveData<Int>(1)
    val selectedmapnumber: LiveData<Int>
        get() = _selectedmapnumber

    fun setSelectedMapNumber(numberSelectedMap: Int) {
        _selectedmapnumber.value = numberSelectedMap
    }

    private var _clippedimage = MutableLiveData<Bitmap>()
    val clippedimage: LiveData<Bitmap>
        get() = _clippedimage

    fun setClippedImage(clippedimage : Bitmap){
        _clippedimage.value = clippedimage
    }

}