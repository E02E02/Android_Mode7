package com.example.android.mode7

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundSelectionBinding
import com.example.android.mode7.databinding.FragmentBackgroundTransformBinding
import com.example.android.mode7.model.Mode7ViewModel

/**
 *
 */
class BackgroundTransformFragment : Fragment() {
    //DataBinding Transform
    private lateinit var binding: FragmentBackgroundTransformBinding

    //DataBinding Selection
    private lateinit var bindingSelection: FragmentBackgroundSelectionBinding

    //SharedViewModel
    private val viewModel: Mode7ViewModel by activityViewModels()

    //Initialize data
    private val mDataset = Datasource().loadMaps()

    //Viewport
    private var mViewPort : ViewPort? = null

    //Modal BottomSheet (BackgroundSelection) show button
    private lateinit var bottomSheetDialogFragment : BackgroundSelectionFragment
    private lateinit var ShowBackgroundSelectionBottomSheet : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBackgroundTransformBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        mViewPort = context?.let { ViewPort(it) }

        bottomSheetDialogFragment = BackgroundSelectionFragment()

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model to a property in the binding class
            mode7ViewModel = viewModel
            // Assign custom view
            viewPort = mViewPort
            // Assign the fragment
            backgroundTransformFragment = this@BackgroundTransformFragment
            // Get Show Modal BottomSheet (BackgroundSelection) button
            ShowBackgroundSelectionBottomSheet = select
        }

        ShowBackgroundSelectionBottomSheet.setOnClickListener {
            bottomSheetDialogFragment.show(requireFragmentManager(), "show selection")
        }

        //val resourceImage : Bitmap = BitmapFactory.decodeResource(context?.resources, viewModel.backgroundimage)
        //val clippedImage : Bitmap = Bitmap.createBitmap(resourceImage,0,0,500,500)

        //val translateCanvas : Canvas = Canvas(clippedImage)
        //val translateMatrix : Matrix = Matrix()
        //translateMatrix.setRotate(45.0F);
        //translateCanvas.drawBitmap(clippedImage, translateMatrix, Paint())

        //viewModel.setClippedImage(clippedImage)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedmapnumber.observe(viewLifecycleOwner, {
            binding.background.setImageResource(mDataset[it].imageResourceId)
        })
    }
}


