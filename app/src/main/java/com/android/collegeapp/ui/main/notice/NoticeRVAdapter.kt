package com.android.collegeapp.ui.main.notice

import com.android.collegeapp.R
import com.android.collegeapp.base.adapter.BaseRVAdapter
import com.android.collegeapp.databinding.LayoutItemNoticeBinding
import com.squareup.picasso.Picasso

class NoticeRVAdapter : BaseRVAdapter<Notice, LayoutItemNoticeBinding>() {
    override fun layout() = R.layout.layout_item_notice
    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutItemNoticeBinding>,
        position: Int
    ) {
        val notice = list[position]
        if(notice.image != ""){
            holder.binding.ivNoticeImage.layoutParams.height = 510
        }
        val dateTime = "Date: ${notice.date}  Time: ${notice.time}"
        holder.binding.apply {
            noticeTitle.text = notice.title
            noticeDateTime.text = dateTime
            ivNoticeImage.setOnClickListener {
                listener?.invoke(holder.itemView,notice,position,"")
            }
        }

        try {
            Picasso.get().load(notice.image).into(holder.binding.ivNoticeImage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}