package com.example.android.mode7

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.example.android.mode7.adapter.ItemAdapter
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundBinding
import com.example.android.mode7.helper.ItemTouchHelperCallback
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

    // Initialize data
    private val myDataset = Datasource().loadMaps()

    //RecyclerView
    private val myAdapter: ItemAdapter by lazy { ItemAdapter( binding.backgroundSelect,myDataset,viewModel) }

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
            // set the custom adapter to the RecyclerView
            adapter = myAdapter
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            // enable optimizations
            this.setHasFixedSize(true);
            // set ItemTouchHelper
            ItemTouchHelper(ItemTouchHelperCallback(myAdapter)).attachToRecyclerView(this)
            // set SnapHelper
            val snapHelper: SnapHelper = GravitySnapHelper(Gravity.START)
            snapHelper.attachToRecyclerView(binding.backgroundSelect)
        }

    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

}