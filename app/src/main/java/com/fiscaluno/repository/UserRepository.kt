package com.fiscaluno.repository

interface UserRepository {

    fun getUserToken(): String
    fun saveUserToken(token: String)

}
