package com.example.android.mode7

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundTransformBinding
import com.example.android.mode7.model.SharedViewModel

/**
 *
 */
class BackgroundTransformFragment : Fragment() {
    //DataBinding
    private lateinit var binding: FragmentBackgroundTransformBinding

    //SharedViewModel
    private val viewModel: SharedViewModel by activityViewModels()

    //Initialize data
    private val mDataset = Datasource().loadMaps()

    //ViewPort

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBackgroundTransformBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val mViewPort = context?.let { ViewPort(it) }
            viewPort = mViewPort
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model to a property in the binding class
            sharedViewModel = viewModel
            // Assign the fragment
            backgroundTransformFragment = this@BackgroundTransformFragment
        }

        val resourceImage : Bitmap = BitmapFactory.decodeResource(context?.resources, mDataset[1])
        val clippedImage : Bitmap = resourceImage.scale(640,640)

        viewModel.setClippedImage(clippedImage)
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedmapnumber.observe(viewLifecycleOwner, {
            binding.background.setImageResource(mDataset[it])
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


