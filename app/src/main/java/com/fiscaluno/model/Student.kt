package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wilder on 11/07/17.
 */

class Student : Parcelable {
    var id: String? = null
    var birthday: String? = null
    var name: String? = null
    var email: String? = null
    var gender: String? = null
    var nacionality: String? = null
    var civilStatus: String? = null
    var address: String? = null
    var city: String? = null
    var state: String? = null
    var phone: String? = null
    var institution: Institution? = null
    var fbInstitutionName: String? = null
    var fbId: String? = null
    //TODO add reviews

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeValue(this.birthday)
        dest.writeString(this.name)
        dest.writeString(this.email)
        dest.writeString(this.gender)
        dest.writeString(this.nacionality)
        dest.writeString(this.civilStatus)
        dest.writeString(this.address)
        dest.writeString(this.city)
        dest.writeString(this.state)
        dest.writeString(this.phone)
        dest.writeString(this.fbInstitutionName)
        dest.writeString(this.fbId)
        dest.writeParcelable(this.institution, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.birthday = `in`.readString()
        this.name = `in`.readString()
        this.email = `in`.readString()
        this.gender = `in`.readString()
        this.nacionality = `in`.readString()
        this.civilStatus = `in`.readString()
        this.address = `in`.readString()
        this.city = `in`.readString()
        this.state = `in`.readString()
        this.phone = `in`.readString()
        this.fbInstitutionName = `in`.readString()
        this.fbId = `in`.readString()
        this.institution = `in`.readParcelable<Institution>(Institution::class.java.classLoader)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Student> = object : Parcelable.Creator<Student> {
            override fun createFromParcel(source: Parcel): Student {
                return Student(source)
            }

            override fun newArray(size: Int): Array<Student?> {
                return arrayOfNulls(size)
            }
        }
    }
}
