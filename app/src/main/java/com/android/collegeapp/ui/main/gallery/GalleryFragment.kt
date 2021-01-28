package com.android.collegeapp.ui.main.gallery

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentGalleryBinding
import com.android.collegeapp.ui.main.BaseFragment
import com.android.collegeapp.util.toast
import com.google.firebase.database.*

class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    private lateinit var databaseReference: DatabaseReference
    private val mAdapter by lazy { GalleryAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Gallery")

        getConvocationImages()
    }

    private fun getConvocationImages() {
        var list : MutableList<String>
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                snapshot.children.forEach { snap ->
                    list.add(snap.getValue(String::class.java)!!)
                }
                mAdapter.addItems(list)
                setUpRv()
            }

            override fun onCancelled(error: DatabaseError) {
                context!!.toast(getString(R.string.something_went_wrong))
            }
        }
        databaseReference.addValueEventListener(listener)
    }

    private fun setUpRv() {
        binding.rvGalleryConvocation.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,3)
            adapter = mAdapter
        }
    }

    override fun setFragmentView() = R.layout.fragment_gallery
}