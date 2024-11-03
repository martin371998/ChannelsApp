package com.example.myapp.entities

import android.os.Parcel
import android.os.Parcelable

data class VideoParams(
    val videoUrl: String,
    val startTime: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(videoUrl)
        parcel.writeInt(startTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoParams> {
        override fun createFromParcel(parcel: Parcel): VideoParams {
            return VideoParams(parcel)
        }

        override fun newArray(size: Int): Array<VideoParams?> {
            return arrayOfNulls(size)
        }
    }
}
