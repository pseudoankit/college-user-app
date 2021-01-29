package com.android.collegeapp.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentHomeBinding
import com.android.collegeapp.ui.BaseFragment
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
            when (i) {
                0 -> {
                    sliderView.imageUrl =
                        "https://firebasestorage.googleapis.com/v0/b/college-app-9d883.appspot.com/o/Gallery%2F%5BB%402929d6jpg?alt=media&token=f3e88848-b26a-43d4-a40d-7ff0464f992c"
                }
                1 -> {
                    sliderView.imageUrl =
                        "https://firebasestorage.googleapis.com/v0/b/college-app-9d883.appspot.com/o/Gallery%2F%5BB%402c36169jpg?alt=media&token=02fe8977-44f4-4d0a-ad85-beaa39531ebf"
                }
                2 -> {
                    sliderView.imageUrl =
                        "https://firebasestorage.googleapis.com/v0/b/college-app-9d883.appspot.com/o/Gallery%2F%5BB%4032737cajpg?alt=media&token=b9b379d8-bfd8-42d7-a883-39fe1893977c"

                }
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            binding.imageSlider.addSliderView(sliderView)
        }
    }
}