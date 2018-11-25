package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.fiscaluno.contracts.SearchContract
import com.google.gson.annotations.SerializedName

/**
 * Created by Wilder on 11/07/17.
 */

data class Institution (
        var id: Int = 0,
        override var name: String? = null,
        val address: String = "",
        val cnpj: String = "",
        val emails: List<String> = emptyList(),
        val phones: List<String> = emptyList(),
        val province: String = "",
        val website: String = "",
        @SerializedName("image_url") var imageUrl: String = "",
        @SerializedName("average_rating") override var averageRating: Float = 0f,
        @SerializedName("rated_by_count") override var ratedByCount: Int = 0,
        val detailedReviews: List<DetailedReview> = emptyList(),
        val generalReviews: List<GeneralReview> = emptyList()
) : RateableEntity(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
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
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(cnpj)
        parcel.writeStringList(emails)
        parcel.writeStringList(phones)
        parcel.writeString(province)
        parcel.writeString(website)
        parcel.writeString(imageUrl)
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

    override fun search(searchPresenter: SearchContract.Presenter, searchFilter: SearchFilter) {
        searchPresenter.searchInstitution(searchFilter)
    }

    override fun setValue(name: String) {
        this.name = name
    }

    override fun getValue(): String = this.name ?: ""

}