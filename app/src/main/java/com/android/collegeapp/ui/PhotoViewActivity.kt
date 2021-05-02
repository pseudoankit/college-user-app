package com.android.collegeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.collegeapp.R
import com.android.collegeapp.ui.main.notice.NoticeFragment.Companion.NOTICE_URI
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_view.*

class PhotoViewActivity : AppCompatActivity() {
//todo opening transition
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        val uri = intent.getStringExtra(NOTICE_URI)!!
        Glide.with(this).load(uri).into(photo)
    }
}