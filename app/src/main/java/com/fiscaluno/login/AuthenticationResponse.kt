package com.fiscaluno.login

data class Body(val id: Int, val token: String)
data class AuthenticationResponse(val body: Body)