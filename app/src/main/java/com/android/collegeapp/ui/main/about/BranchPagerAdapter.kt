package com.android.collegeapp.ui.main.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.android.collegeapp.R
import com.android.collegeapp.databinding.LayoutItemBranchBinding

class BranchPagerAdapter() : PagerAdapter() {


    private lateinit var binding: LayoutItemBranchBinding
    private lateinit var mList: List<Branch>

    override fun getCount() = mList.size

     fun addItems(list: MutableList<Branch>){
        this.mList = list
         notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, obj: Any) = (view == obj)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = DataBindingUtil.inflate(LayoutInflater.from(container.context),R.layout.layout_item_branch,container,false)

        val item = mList[position]
        binding.tvBranchName.text = item.title
        binding.ivBranchIcon.setImageResource(item.image)

        container.addView(binding.root,0)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}