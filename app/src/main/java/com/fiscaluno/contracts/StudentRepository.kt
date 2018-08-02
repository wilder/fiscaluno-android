package com.fiscaluno.contracts

import com.fiscaluno.model.Student

interface StudentRepository {

    fun saveUser(student: Student)

}