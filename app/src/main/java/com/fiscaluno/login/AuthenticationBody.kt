package com.fiscaluno.login

import com.google.gson.annotations.SerializedName

data class AuthenticationBody(
        @SerializedName("facebook_id") val facebookId: String,
        @SerializedName("fb_token") val facebookToken: String)