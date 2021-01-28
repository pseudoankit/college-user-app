package com.android.collegeapp.ui.main.faculty

import com.android.collegeapp.R
import com.android.collegeapp.adapter.BaseRVAdapter
import com.android.collegeapp.databinding.LayoutItemFacultyBinding
import com.squareup.picasso.Picasso

class FacultyAdapter : BaseRVAdapter<Faculty, LayoutItemFacultyBinding>() {
    override fun layout() = R.layout.layout_item_faculty
    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutItemFacultyBinding>,
        position: Int
    ) {
        val faculty = list[position]
        holder.binding.faculty = faculty
        Picasso.get().load(faculty.image).into(holder.binding.ivFacultyImage)
    }

}