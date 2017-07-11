package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wilder on 11/07/17.
 */

class GeneralReview : Review, Parcelable {
    var suggestion: String? = null
    var rate: Int? = null //from 1 to 5 -
    var pros: String? = null
    var cons: String? = null
    var description: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.suggestion)
        dest.writeValue(this.rate)
        dest.writeString(this.pros)
        dest.writeString(this.cons)
        dest.writeString(this.description)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.suggestion = `in`.readString()
        this.rate = `in`.readValue(Int::class.java.classLoader) as Int
        this.pros = `in`.readString()
        this.cons = `in`.readString()
        this.description = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<GeneralReview> = object : Parcelable.Creator<GeneralReview> {
            override fun createFromParcel(source: Parcel): GeneralReview {
                return GeneralReview(source)
            }

            override fun newArray(size: Int): Array<GeneralReview?> {
                return arrayOfNulls(size)
            }
        }
    }
}
