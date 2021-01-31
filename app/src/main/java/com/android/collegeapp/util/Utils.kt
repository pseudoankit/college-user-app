package com.android.collegeapp.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


fun Context.showImageOnDialog(uri: Uri) {
    val builder = Dialog(this)
    builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
    builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    builder.setOnDismissListener{
        builder.dismiss()
    }
    val imageView = PhotoView(this)
    Picasso.get().load(uri).into(imageView)
    builder.addContentView(
        imageView, RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    )
    builder.show()
}

fun Context.viewPdf(uri: Uri) {
    val browserIntent = Intent(Intent.ACTION_VIEW)
    browserIntent.setDataAndType(uri, "application/pdf")
    val chooser = Intent.createChooser(browserIntent, "title")
    chooser.flags = Intent.FLAG_ACTIVITY_NEW_TASK // optional
    startActivity(chooser)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackBar ->
        snackBar.setAction("Ok") {
            snackBar.dismiss()
        }
    }.show()
}