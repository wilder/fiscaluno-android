package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 11/07/17.
 */

data class Institution (
        val id: String,
        override var name: String,
        val address: String,
        val cnpj: String,
        val emails: List<String>,
        val phones: List<String>,
        val province: String,
        val website: String,
        @SerializedName("image_url") val imageUri: String,
        @SerializedName("average_rating") override var averageRating: Float,
        @SerializedName("rated_by_count") override var ratedByCount: Int,
        val detailedReviews: List<DetailedReview>,
        val generalReviews: List<GeneralReview>
) : RateableEntity(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.createTypedArrayList(DetailedReview.CREATOR),
            parcel.createTypedArrayList(GeneralReview.CREATOR))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(cnpj)
        parcel.writeStringList(emails)
        parcel.writeStringList(phones)
        parcel.writeString(province)
        parcel.writeString(website)
        parcel.writeString(imageUri)
        parcel.writeFloat(averageRating)
        parcel.writeInt(ratedByCount)
        parcel.writeTypedList(detailedReviews)
        parcel.writeTypedList(generalReviews)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Institution> {
        override fun createFromParcel(parcel: Parcel): Institution = Institution(parcel)

        override fun newArray(size: Int): Array<Institution?> = arrayOfNulls(size)
    }
}