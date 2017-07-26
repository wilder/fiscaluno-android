package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wilder on 11/07/17.
 */

class DetailedReview : Review, Parcelable {
    var type: String ? = null
    var value: Int ? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeString(this.type)
        dest.writeInt(this.value!!)
    }

    constructor() {}

    protected constructor(`in`: Parcel) : super(`in`) {
        this.type = `in`.readString()
        this.value = `in`.readInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<DetailedReview> = object : Parcelable.Creator<DetailedReview> {
            override fun createFromParcel(source: Parcel): DetailedReview {
                return DetailedReview(source)
            }

            override fun newArray(size: Int): Array<DetailedReview?> {
                return arrayOfNulls(size)
            }
        }
    }
}
