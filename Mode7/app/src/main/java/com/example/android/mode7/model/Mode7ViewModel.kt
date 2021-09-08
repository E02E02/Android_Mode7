package com.example.android.mode7.model

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Mode7ViewModel : ViewModel() {

    //Background image selection
    private val _selectedmapnumber = MutableLiveData<Int>(0)
    val selectedmapnumber: LiveData<Int>
        get() = _selectedmapnumber

    fun setSelectedMapNumber(numberSelectedMap: Int) {
        _selectedmapnumber.value = numberSelectedMap
    }

    //Mode7 parameters
    private val _H = MutableLiveData<Float>(40.0F)
    val h: LiveData<Float>
        get() = _H

    fun up() {
        _H.value?.minus(10)
    }

    fun down() {
        _H.value?.plus(10)
    }

    private val _V = MutableLiveData<Float>(0.0F)
    val v: LiveData<Float>
        get() = _V

    fun left() {
        _V.value?.minus(10)
    }

    fun right() {
        _V.value?.plus(10)
    }

    //Background image Mode7 transform to apply
    private var _backgroundimage = MutableLiveData<Int>()
    val backgroundimage: LiveData<Int>
        get() = _backgroundimage

    fun setBackgroundImage(backgroundimage : Int){
        _backgroundimage.value = backgroundimage
    }

    //Screen image Mode7 transform applied
    private var _clippedimage = MutableLiveData<Bitmap>()
    val clippedimage: LiveData<Bitmap>
        get() = _clippedimage

    fun setClippedImage(clippedimage : Bitmap){
        _clippedimage.value = clippedimage
    }

}