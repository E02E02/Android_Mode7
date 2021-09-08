package com.example.android.mode7.adapter

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mode7.R
import com.example.android.mode7.model.Background

/**
 * Adapter for the [RecyclerView] in [BackgroundSelectionFragment]. Displays [Map] data object.
 */
class BackgroundSelectionAdapter(
    private val context: RecyclerView,
    private val dataset: List<Background>,
    private val onItemTouched: (position: Int) -> Unit,
): RecyclerView.Adapter<BackgroundSelectionAdapter.BackgroundSelectionHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class BackgroundSelectionHolder(private val view: View, private val onItemTouched: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnTouchListener {
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)

        init {
            view.setOnTouchListener(this)
        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            onItemTouched(absoluteAdapterPosition)
            return true
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundSelectionAdapter.BackgroundSelectionHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.map_item, parent, false)

        //Create ItemViewHolder(adapterLayout)
        return BackgroundSelectionAdapter.BackgroundSelectionHolder(adapterLayout, onItemTouched)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(viewHolder: BackgroundSelectionHolder, position: Int) {
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