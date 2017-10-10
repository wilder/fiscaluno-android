package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wilder on 11/07/17.
 */

class Institution() : Parcelable {
    var id: String? = null
    var name: String? = null
    var address: String? = null
    var cnpj: String? = null
    var email: String? = null
    var website: String? = null
    var phoneNumber: String? = null
    var imageUri: String? = null
    var averageRating: Float? = null
    var reviewdBy: Int? = null
    var detailedReviews: List<DetailedReview>? = null
    var generalReviews: List<GeneralReview>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        address = parcel.readString()
        cnpj = parcel.readString()
        email = parcel.readString()
        website = parcel.readString()
        phoneNumber = parcel.readString()
        imageUri = parcel.readString()
        averageRating = parcel.readValue(Float::class.java.classLoader) as? Float
        reviewdBy = parcel.readValue(Int::class.java.classLoader) as? Int
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

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Institution> {
        override fun createFromParcel(parcel: Parcel): Institution {
            return Institution(parcel)
        }

        override fun newArray(size: Int): Array<Institution?> {
            return arrayOfNulls(size)
        }
    }

}
