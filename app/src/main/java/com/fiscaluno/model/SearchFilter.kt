package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

data class SearchFilter(
        var searchableEntity: SearchableEntity? = null,
        private val city: String? = null,
        private val state: String? = null,
        private val rate: Float = 0f,
        private val sort: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable<SearchableEntity>(SearchableEntity::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(searchableEntity, flags)
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