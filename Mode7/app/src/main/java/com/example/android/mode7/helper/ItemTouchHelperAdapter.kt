package com.example.android.mode7.helper

interface ItemTouchHelperAdapter {
    fun onMoveItem(from: Int, to: Int): Boolean
    fun onItemChanged(from: Int)
}