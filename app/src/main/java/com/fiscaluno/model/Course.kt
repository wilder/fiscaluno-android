package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

class Course : RateableEntity, Parcelable {

    override var name: String = ""
    override var averageRating: Float = 0f
    override var ratedByCount: Int = 0

    var id: String? = null
    var institution: Institution? = null


    constructor() {}

    constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.name = `in`.readString()
        this.ratedByCount = `in`.readInt()
        this.averageRating = `in`.readFloat()
        this.institution = `in`.readParcelable(Institution::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(this.averageRating)
        dest.writeValue(this.ratedByCount)
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
