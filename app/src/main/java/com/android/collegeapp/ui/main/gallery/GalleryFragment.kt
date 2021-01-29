package com.android.collegeapp.ui.main.gallery

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentGalleryBinding
import com.android.collegeapp.ui.BaseFragment
import com.android.collegeapp.util.toast
import com.google.firebase.database.*

class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    private lateinit var databaseReference: DatabaseReference

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Gallery")

        getImages(getString(R.string.convocation),binding.rvGalleryConvocation)
        getImages(getString(R.string.republic_day),binding.rvGalleryRepublicDay)
    }

    private fun getImages(event: String,rv: RecyclerView) {
        //todo fb simp
        var list : MutableList<String>

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                snapshot.children.forEach { snap ->
                    list.add(snap.value!! as String)
                }
                setUpRv(rv,list)
            }

            override fun onCancelled(error: DatabaseError) {
                context!!.toast(getString(R.string.something_went_wrong))
            }
        }
        databaseReference.child(event).addValueEventListener(listener)
    }

    private fun setUpRv(rv: RecyclerView, list: MutableList<String>) {
        val mAdapter = GalleryRVAdapter()
        mAdapter.addItems(list)
        rv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,3)
            adapter = mAdapter
        }
    }

    override fun setFragmentView() = R.layout.fragment_gallery
}