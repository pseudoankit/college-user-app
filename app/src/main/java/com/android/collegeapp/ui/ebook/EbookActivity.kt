package com.android.collegeapp.ui.ebook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.android.collegeapp.R
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.collegeapp.databinding.ActivityEbookBinding
import com.android.collegeapp.util.hide
import com.android.collegeapp.util.show
import com.android.collegeapp.util.toast
import com.android.collegeapp.util.viewPdf
import com.google.firebase.database.*


class EbookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEbookBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: MutableList<Ebook>
    private val mAdapter by lazy { EbookRVAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ebook)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Pdf")

        getPdf()

    }

    private fun getPdf() {
        binding.progressBar.show()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                snapshot.children.forEach { snap ->
                    list.add(snap.getValue(Ebook::class.java)!!)
                }
                setUpRv()
            }

            override fun onCancelled(error: DatabaseError) {
                toast(getString(R.string.something_went_wrong))
            }
        }
        databaseReference.addValueEventListener(listener)
    }

    private fun setUpRv() {
        binding.progressBar.hide()
        binding.rvEbook.show()
        mAdapter.addItems(list)
        binding.rvEbook.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@EbookActivity)
            adapter = mAdapter
        }
        mAdapter.listener = { _, ebook, _, type ->
            if (type == DOWNLOAD) {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(ebook.url)
                    startActivity(this)
                }
            } else if (type == VIEW_PDF) {
                viewPdf(Uri.parse(ebook.url))
            }
        }
    }

    companion object {
        const val PDF_URL_Intent = "url"
        const val DOWNLOAD = "download"
        const val VIEW_PDF = "view"
    }

}