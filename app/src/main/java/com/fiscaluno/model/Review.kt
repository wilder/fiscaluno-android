package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

import java.util.Date

/**
 * Created by Wilder on 11/07/17.
 */

open class Review : Parcelable {
    var id: String? = null
    var createdAt: Date? = null
    var rate: Float? = null //from 1 to 5 -
    var course: String ? = null
    var institution: Institution? = null
    var student: Student? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(this.id)
        dest.writeLong(if (this.createdAt != null) this.createdAt!!.time else -1)
        dest.writeString(this.course)
        dest.writeValue(this.rate)
        dest.writeParcelable(this.institution, flags)
        dest.writeParcelable(this.student, flags)
    }

    override fun toString(): String {
        return "Review(id=$id, createdAt=$createdAt, rate=$rate, course=$course, institution=$institution, student=$student)"
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        val tmpCreatedAt = `in`.readLong()
        this.rate = `in`.readValue(Float::class.java.classLoader) as Float
        this.createdAt = if (tmpCreatedAt.toInt() == -1) null else Date(tmpCreatedAt)
        this.course = `in`.readString()
        this.institution = `in`.readParcelable<Institution>(Institution::class.java.classLoader)
        this.student = `in`.readParcelable<Student>(Student::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Review> = object : Parcelable.Creator<Review> {
            override fun createFromParcel(source: Parcel): Review {
                return Review(source)
            }

            override fun newArray(size: Int): Array<Review?> {
                return arrayOfNulls(size)
            }
        }
    }
}
