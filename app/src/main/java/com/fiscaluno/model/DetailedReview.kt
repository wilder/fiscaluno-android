package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 11/07/17.
 */

class DetailedReview : Review, Parcelable {
    @SerializedName("review_type") var type: Int = 0
    var description: String = "description"

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
        dest.writeInt(this.type)
        dest.writeString(this.description)
    }

    constructor() {}

    protected constructor(`in`: Parcel) : super(`in`) {
        this.type = `in`.readInt()
        this.description = `in`.readString()
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
