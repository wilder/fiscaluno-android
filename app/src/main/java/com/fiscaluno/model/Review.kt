package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 * Created by Wilder on 11/07/17.
 */

open class Review : Parcelable {
    var id: Int = 0
    @SerializedName("created_at") var createdAt: Date? = null
    var rate: Float? = null //from 1 to 5 -
    var course: String ? = null
    @SerializedName("institution_id") var institutionId: Int? = null
    @SerializedName("student_id") var studentId: Int? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeLong(if (this.createdAt != null) this.createdAt!!.time else -1)
        dest.writeString(this.course)
        dest.writeValue(this.rate)
        dest.writeValue(this.institutionId)
        dest.writeValue(this.studentId)
    }

    override fun toString(): String {
        return "Review(id=$id, createdAt=$createdAt, rate=$rate, course=$course, institutioIdn=$institutionId, student=$studentId)"
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        val tmpCreatedAt = `in`.readLong()
        this.rate = `in`.readValue(Float::class.java.classLoader) as Float
        this.createdAt = if (tmpCreatedAt.toInt() == -1) null else Date(tmpCreatedAt)
        this.course = `in`.readString()
        this.institutionId = `in`.readInt()
        this.studentId = `in`.readInt()
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
