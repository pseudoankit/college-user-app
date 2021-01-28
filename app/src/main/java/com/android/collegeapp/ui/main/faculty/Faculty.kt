package com.android.collegeapp.ui.main.faculty

import android.os.Parcel
import android.os.Parcelable

data class Faculty(
    val name: String ="",
    val email: String ="",
    val post: String ="",
    val image: String ="",
    val key: String ="",
    val category: String =""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(post)
        parcel.writeString(image)
        parcel.writeString(key)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Faculty> {
        override fun createFromParcel(parcel: Parcel): Faculty {
            return Faculty(parcel)
        }

        override fun newArray(size: Int): Array<Faculty?> {
            return arrayOfNulls(size)
        }
    }
}
