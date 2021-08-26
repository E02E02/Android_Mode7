package com.example.android.mode7

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.mode7.databinding.FragmentScreenBinding
import com.example.android.mode7.model.Mode7ViewModel

/**
 *
 */
class ScreenFragment : Fragment() {
    //DataBinding
    private lateinit var binding: FragmentScreenBinding

    //SharedViewModel
    private val viewModel: Mode7ViewModel by activityViewModels()

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

        binding.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the view model to a property in the binding class
            mode7ViewModel = viewModel
            // Assign the fragment
            screenFragment = this@ScreenFragment
        }
    }
}

@BindingAdapter("android:imageBitmap")
fun loadImage(view: ImageView, bitmap: Bitmap?) {
    view.setImageBitmap(bitmap)
}



