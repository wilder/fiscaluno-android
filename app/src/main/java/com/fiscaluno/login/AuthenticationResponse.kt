package com.fiscaluno.login

import com.google.gson.annotations.SerializedName

data class Body(val id: Int, val token: String)
data class AuthenticationResponse(@SerializedName("Body") val body: Body)