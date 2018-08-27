package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 11/07/17.
 */

data class GeneralReview (
    var title: String? = null,
    var suggestion: String? = null,
    var pros: String? = null,
    var cons: String? = null,
    var description: String? = null,
    var payment: Double? = null,
    var startYear: Int? = null,
    @SerializedName("course_info") var courseInfo: CourseInfo? = null
) : Review() {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readParcelable(CourseInfo::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(title)
        parcel.writeString(suggestion)
        parcel.writeString(pros)
        parcel.writeString(cons)
        parcel.writeString(description)
        parcel.writeValue(payment)
        parcel.writeValue(startYear)
        parcel.writeParcelable(courseInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeneralReview> {
        override fun createFromParcel(parcel: Parcel): GeneralReview {
            return GeneralReview(parcel)
        }

        override fun newArray(size: Int): Array<GeneralReview?> {
            return arrayOfNulls(size)
        }
    }
}
