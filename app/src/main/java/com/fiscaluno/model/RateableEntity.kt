package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

open class RateableEntity() : Parcelable {

    open var name: String? = null
    var averageRating: Float = 0f
    var reviewdBy: Int = 0

    constructor(`in`: Parcel) : this() {
        this.name = `in`.readString()
        this.reviewdBy = `in`.readInt()
        this.averageRating = `in`.readFloat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(this.averageRating)
        parcel.writeValue(this.reviewdBy)
        parcel.writeString(this.name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RateableEntity> {
        override fun createFromParcel(parcel: Parcel): RateableEntity = RateableEntity(parcel)

        override fun newArray(size: Int): Array<RateableEntity?> = arrayOfNulls(size)
    }


}
