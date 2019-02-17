package com.fiscaluno.contracts

import com.fiscaluno.model.Student
import java.util.concurrent.Future

interface StudentRepository {
    fun saveUser(student: Student)
    fun getUserById(id: String): Student?
}