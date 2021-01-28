package com.android.collegeapp.ui.main.notice

import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.collegeapp.R
import com.android.collegeapp.databinding.FragmentNoticeBinding
import com.android.collegeapp.ui.main.BaseFragment
import com.android.collegeapp.util.hide
import com.android.collegeapp.util.show
import com.android.collegeapp.util.toast
import com.google.firebase.database.*

class NoticeFragment : BaseFragment<FragmentNoticeBinding>() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var list: MutableList<Notice>
    private val adapter by lazy { NoticeAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Notice")

        getNotice()

    }

    private fun getNotice() {
        binding.progressBar.show()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                if (!snapshot.exists()) {
                    binding.noNoticeFound.show()
                    binding.rvNotice.hide()
                } else {
                    binding.noNoticeFound.hide()
                    binding.rvNotice.show()
                    snapshot.children.forEach { snap ->
                        list.add(snap.getValue(Notice::class.java)!!)
                    }
                    setUpRv()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                context!!.toast(getString(R.string.something_went_wrong))
            }
        }
        databaseReference.addValueEventListener(listener)
    }

    private fun setUpRv() {
        binding.progressBar.hide()
        adapter.addItems(list)
        binding.rvNotice.setHasFixedSize(true)
        binding.rvNotice.layoutManager = LinearLayoutManager(context)
        binding.rvNotice.adapter = adapter
    }

    override fun setFragmentView() = R.layout.fragment_notice

}