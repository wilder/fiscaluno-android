package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wilder on 11/07/17.
 */

class Institution() : Parcelable {
    var id: Int? = null
    var name: String? = null
    var address: String? = null
    var cnpj: String? = null
    var email: String? = null
    var website: String? = null
    var phoneNumber: String? = null
    var imageUri: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(this.id)
        dest.writeString(this.name)
        dest.writeString(this.address)
        dest.writeString(this.cnpj)
        dest.writeString(this.email)
        dest.writeString(this.website)
        dest.writeString(this.phoneNumber)
        dest.writeString(this.imageUri)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.name = `in`.readString()
        this.address = `in`.readString()
        this.cnpj = `in`.readString()
        this.email = `in`.readString()
        this.website = `in`.readString()
        this.phoneNumber = `in`.readString()
        this.imageUri = `in`.readString()

    }

    companion object {

        val CREATOR: Parcelable.Creator<Institution> = object : Parcelable.Creator<Institution> {
            override fun createFromParcel(source: Parcel): Institution {
                return Institution(source)
            }

            override fun newArray(size: Int): Array<Institution?> {
                return arrayOfNulls(size)
            }
        }
    }
}
