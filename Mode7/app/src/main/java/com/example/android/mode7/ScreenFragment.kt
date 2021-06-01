package com.example.android.mode7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentScreenBinding
import com.example.android.mode7.model.SharedViewModel

/**
 *
 */
class ScreenFragment : Fragment() {
    //DataBinding
    private lateinit var binding: FragmentScreenBinding

    //SharedViewModel
    private val viewModel: SharedViewModel by activityViewModels()

    // Initialize data
    val myDataset = Datasource().loadMaps()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentScreenBinding.inflate(inflater, container, false)
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
            screenFragment = this@ScreenFragment
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.selectedmapnumber.observe(viewLifecycleOwner, Observer {
            val item = myDataset[it] // it = selectedmapnumber
            binding.screen.setImageResource(item.imageResourceId)
        })

    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }
}