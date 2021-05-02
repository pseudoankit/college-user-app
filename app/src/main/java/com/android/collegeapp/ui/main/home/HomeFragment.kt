package com.android.collegeapp.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentHomeBinding
import com.android.collegeapp.base.BaseFragment
import com.smarteist.autoimageslider.DefaultSliderView
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun setFragmentView() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageSlider()

        binding.ivMap.setOnClickListener { openMap() }
    }

    private fun openMap() {
        val address = "Kasmar Bokaro"
        Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=$address")).apply {
            setPackage("com.google.android.apps.maps")
            startActivity(this)
        }
    }

    private fun imageSlider() {
        binding.imageSlider.apply {
            setIndicatorAnimation(IndicatorAnimations.FILL)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            scrollTimeInSec = 1
        }
        for (i in 0..3) {
            val sliderView = DefaultSliderView(context)

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            binding.imageSlider.addSliderView(sliderView)
        }
    }
}