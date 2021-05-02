package com.android.collegeapp.ui.main.about

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentAboutBinding
import com.android.collegeapp.base.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    private lateinit var viewPager: ViewPager
    private val mAdapter by lazy { BranchPagerAdapter() }
    private lateinit var list: MutableList<Branch>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list = mutableListOf()
        list.add(Branch(R.drawable.ic_mechanical, getString(R.string.mechanical)))
        list.add(Branch(R.drawable.ic_computer,getString(R.string.cse)))

        mAdapter.addItems(list)
        binding.viewPager.adapter = mAdapter

    }

    override fun setFragmentView() = R.layout.fragment_about

}