package com.fiscaluno.repository

import com.fiscaluno.model.Student

interface UserRepository {

    fun getUserToken(): String
    fun saveUserToken(token: String)

}
