package com.example.android.mode7.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //val swipeFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.UP
        //val swipeFlags = 0
        val dragFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags( dragFlags, swipeFlags )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // Notify Adapter of the moved item!
        adapter.onMoveItem(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition);
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemChanged(viewHolder.bindingAdapterPosition)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        // Allows for long click so items can be dragged, moved up or down in the list.
        return true
    }

}