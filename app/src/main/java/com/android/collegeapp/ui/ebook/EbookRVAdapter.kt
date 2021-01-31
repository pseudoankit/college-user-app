package com.android.collegeapp.ui.ebook

import com.android.collegeapp.R
import com.android.collegeapp.adapter.BaseRVAdapter
import com.android.collegeapp.databinding.LayoutItemEbookBinding
import com.android.collegeapp.ui.ebook.EbookActivity.Companion.LISTENER_DOWNLOAD
import com.android.collegeapp.ui.ebook.EbookActivity.Companion.LISTENER_VIEW_PDF

class EbookRVAdapter : BaseRVAdapter<Ebook,LayoutItemEbookBinding>(){
    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutItemEbookBinding>,
        position: Int
    ) {
        val ebook = list[position]
        holder.binding.tvEbookTitle.text = ebook.title

        holder.binding.ivEbookDownload.setOnClickListener {
            listener?.invoke(holder.itemView,ebook,position,LISTENER_DOWNLOAD)
        }

        holder.binding.root.setOnClickListener {
            listener?.invoke(holder.itemView,ebook,position, LISTENER_VIEW_PDF)
        }

    }

    override fun layout() = R.layout.layout_item_ebook

}