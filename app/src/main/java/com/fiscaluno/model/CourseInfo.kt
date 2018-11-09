package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CourseInfo (
        @SerializedName("course_id") val courseId: Int = 0,
        @SerializedName("course_type") val courseType: String? = null,
        @SerializedName("period") val period: String? = null,
        @SerializedName("start_year") val startYear: Int? = null,
        @SerializedName("course_name") val courseName: String,
        @SerializedName("monthly_payment_value") val monthlyPaymentValue: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(courseId)
        parcel.writeString(courseType)
        parcel.writeString(period)
        parcel.writeValue(startYear)
        parcel.writeString(courseName)
        parcel.writeValue(monthlyPaymentValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseInfo> {
        override fun createFromParcel(parcel: Parcel): CourseInfo {
            return CourseInfo(parcel)
        }

        override fun newArray(size: Int): Array<CourseInfo?> {
            return arrayOfNulls(size)
        }
    }
}