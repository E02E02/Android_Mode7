package com.example.android.mode7.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mode7.BackgroundFragment
import com.example.android.mode7.R
import com.example.android.mode7.model.BackgroundMap

/**
 * Adapter for the [RecyclerView] in [BackgroundFragment]. Displays [Map] data object.
 */
class ItemAdapter(
    private val context: RecyclerView,
    private val dataset: List<BackgroundMap>,
    private val onItemClicked: (position: Int) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(private val view: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)

        override fun onClick(view : View) {
            val position = absoluteAdapterPosition
            onItemClicked(position)
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.map_item, parent, false)

        return ItemViewHolder(adapterLayout, onItemClicked)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        viewHolder.textView.text = context.resources.getString(item.stringResourceId)
        viewHolder.imageView.setImageResource(item.imageResourceId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

}