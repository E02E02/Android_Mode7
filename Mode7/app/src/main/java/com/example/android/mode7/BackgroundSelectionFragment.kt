package com.example.android.mode7

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mode7.adapter.BackgroundSelectionAdapter
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundSelectionBinding
import com.example.android.mode7.model.Mode7ViewModel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 */
class BackgroundSelectionFragment : BottomSheetDialogFragment() {
    //DataBinding
    private lateinit var binding: FragmentBackgroundSelectionBinding

    //SharedViewModel
    private val viewModel: Mode7ViewModel by activityViewModels()

    // Initialize data
    private var mDataset = Datasource().loadMaps()

    //RecyclerView
    private var mAdapter : BackgroundSelectionAdapter? = null

    companion object {
        const val TAG = "BackgroundSelectionFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBackgroundSelectionBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model to a property in the binding class
            mode7ViewModel = viewModel
            // Assign the fragment
            backgroundSelectionFragment = this@BackgroundSelectionFragment
        }

        mAdapter = BackgroundSelectionAdapter(binding.backgroundSelection, mDataset){position -> onListItemTouched(position)}

        binding.backgroundSelection.apply {
            // set a LinearLayoutManager to handle Android
            // set the custom adapter to the RecyclerView
            adapter = mAdapter
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            // enable optimizations
            setHasFixedSize(true);
            // set SnapHelper - Gravity
            GravitySnapHelper(Gravity.START).attachToRecyclerView(binding.backgroundSelection)
        }
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun onListItemTouched(position: Int) {
        viewModel.setSelectedMapNumber(position)
    }
}
