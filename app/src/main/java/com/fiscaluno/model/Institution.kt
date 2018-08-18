package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 11/07/17.
 */

class Institution : RateableEntity, Parcelable {
    @SerializedName("ID")
    var id: String? = null

    @SerializedName("Name")
    override var name: String? = null

    @SerializedName("Address")
    var address: String? = null

    @SerializedName("Cnpj")
    var cnpj: String? = null

    @SerializedName("Email")
    var email: String? = null

    @SerializedName("Website")
    var website: String? = null

    @SerializedName("Phone")
    var phoneNumber: String? = null

    @SerializedName("ImageUri")
    var imageUri: String? = null

    var detailedReviews: List<DetailedReview>? = null
    var generalReviews: List<GeneralReview>? = null

    constructor() {}

    constructor(parcel: Parcel) {
        id = parcel.readString()
        name = parcel.readString()
        address = parcel.readString()
        cnpj = parcel.readString()
        email = parcel.readString()
        website = parcel.readString()
        phoneNumber = parcel.readString()
        imageUri = parcel.readString()
        averageRating = parcel.readValue(Float::class.java.classLoader) as Float
        reviewdBy = parcel.readValue(Int::class.java.classLoader) as Int
        detailedReviews = parcel.createTypedArrayList(DetailedReview.CREATOR)
        generalReviews = parcel.createTypedArrayList(GeneralReview.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(cnpj)
        parcel.writeString(email)
        parcel.writeString(website)
        parcel.writeString(phoneNumber)
        parcel.writeString(imageUri)
        parcel.writeValue(averageRating)
        parcel.writeValue(reviewdBy)
        parcel.writeTypedList(detailedReviews)
        parcel.writeTypedList(generalReviews)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Institution> {
        override fun createFromParcel(parcel: Parcel): Institution = Institution(parcel)

        override fun newArray(size: Int): Array<Institution?> = arrayOfNulls(size)
    }

}
