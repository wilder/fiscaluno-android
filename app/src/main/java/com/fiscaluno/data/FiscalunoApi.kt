package com.fiscaluno.data

import com.fiscaluno.login.AuthenticationBody
import com.fiscaluno.login.AuthenticationResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FiscalunoApi {

    @Headers("X-Client-ID: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNsaWVudCI6ImFueSJ9LCJpc3MiOiJtdSJ9.k7uGe0qbuwwxAp_UOVGVft4eAJwDc_FnaZg-pmwzUZ0")
    @POST("https://fiscaluno-mu.herokuapp.com/users")
    fun authenticate(@Body authenticationBody: AuthenticationBody): Observable<Response<AuthenticationResponse>>

}