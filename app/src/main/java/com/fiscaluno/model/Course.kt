package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

class Course : Parcelable {

    var id: String? = null
    var name: String? = null
    var institution: Institution? = null
    var averageRating: Float? = null
    var reviewdBy: Int? = null


    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.name = `in`.readString()
        this.reviewdBy = `in`.readInt()
        this.averageRating = `in`.readFloat()
        this.institution = `in`.readParcelable<Institution>(Institution::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(this.averageRating)
        dest.writeValue(this.reviewdBy!!)
        dest.writeString(this.name)
        dest.writeString(this.id)
        dest.writeParcelable(this.institution, flags)
    }

    override fun describeContents(): Int =
            0

    companion object {

        val CREATOR: Parcelable.Creator<Course> = object : Parcelable.Creator<Course> {
            override fun createFromParcel(source: Parcel): Course {
                return Course(source)
            }

            override fun newArray(size: Int): Array<Course?> {
                return arrayOfNulls(size)
            }
        }
    }

}
