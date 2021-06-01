package com.example.android.mode7

import android.util.Log

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.android.mode7.adapter.ItemAdapter
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundBinding
import com.example.android.mode7.model.SharedViewModel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper

/**
 *
 */
class BackgroundFragment : Fragment() {
    //DataBinding
    private lateinit var binding: FragmentBackgroundBinding

    //SharedViewModel
    private val viewModel: SharedViewModel by activityViewModels()

    //RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>

    // Initialize data
    val myDataset = Datasource().loadMaps()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBackgroundBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model to a property in the binding class
            shareViewModel = viewModel
            // Assign the fragment
            backgroundFragment = this@BackgroundFragment
        }*/

        binding.backgroundSelect.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            // set the custom adapter to the RecyclerView
            adapter = ItemAdapter(this, myDataset)
            // enable optimizations
            this.setHasFixedSize(true);
        }

        val snapHelper: SnapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(binding.backgroundSelect)

        val callback: ItemTouchHelper.Callback = ItemTouchCallback(binding.backgroundSelect, viewModel)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.backgroundSelect)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

}

class ItemTouchCallback(private val context: RecyclerView, private val viewModel : SharedViewModel
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags( dragFlags, swipeFlags )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true;
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.d("debug", "image : " + viewHolder.bindingAdapterPosition.toString())
        viewModel.setSelectedMapNumber(viewHolder.bindingAdapterPosition)
    }

}