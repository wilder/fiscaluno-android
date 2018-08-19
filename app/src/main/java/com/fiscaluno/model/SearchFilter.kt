package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

data class SearchFilter(
        private val entityName: String,
        private val city: String,
        private val state: String,
        private val rate: Float,
        private val sort: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(entityName)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeFloat(rate)
        parcel.writeString(sort)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<SearchFilter> {
        override fun createFromParcel(parcel: Parcel): SearchFilter = SearchFilter(parcel)

        override fun newArray(size: Int): Array<SearchFilter?> = arrayOfNulls(size)
    }
}