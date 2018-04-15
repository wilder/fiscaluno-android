package com.fiscaluno.contracts

import com.fiscaluno.model.Student

interface UserRepositoryContract {

    fun saveUser(student: Student)

}