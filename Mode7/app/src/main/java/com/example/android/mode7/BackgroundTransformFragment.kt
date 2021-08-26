package com.example.android.mode7

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.mode7.data.Datasource
import com.example.android.mode7.databinding.FragmentBackgroundTransformBinding
import com.example.android.mode7.model.Mode7ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 *
 */
class BackgroundTransformFragment : Fragment() {
    //DataBinding
    private lateinit var binding: FragmentBackgroundTransformBinding

    //SharedViewModel
    private val viewModel: Mode7ViewModel by activityViewModels()

    //Initialize data
    private val mDataset = Datasource().loadMaps()

    //Viewport
    private var mViewPort : ViewPort? = null

    //Modal BottomSheet (BackgroundSelection) show button
    private lateinit var ShowBackgroundSelectionBottomSheetButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentBackgroundTransformBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        mViewPort = context?.let { ViewPort(it) }

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
            ShowBackgroundSelectionBottomSheetButton = select
        }

        ShowBackgroundSelectionBottomSheetButton.setOnClickListener{
            BackgroundSelectionFragment().apply {
                show(supportFragmentManager, BackgroundSelectionFragment.TAG)
            }

            val imgClose=view.findViewById<ImageView>(R.id.img_close)
            imgClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.show()

        }

        val resourceImage : Bitmap = BitmapFactory.decodeResource(context?.resources, mDataset[viewModel.selectedmapnumber.value?.toInt()!!])
        val clippedImage : Bitmap = Bitmap.createBitmap(resourceImage,0,0,500,500)

        //val translateCanvas : Canvas = Canvas(clippedImage)
        //val translateMatrix : Matrix = Matrix()
        //translateMatrix.setRotate(45.0F);
        //translateCanvas.drawBitmap(clippedImage, translateMatrix, Paint())
        viewModel.setClippedImage(clippedImage)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.selectedmapnumber.observe(viewLifecycleOwner, {
            binding.background.setImageResource(mDataset[it])
        })
    }
}


