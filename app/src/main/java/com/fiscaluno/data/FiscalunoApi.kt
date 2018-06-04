package com.fiscaluno.data

import com.fiscaluno.model.Institution
import com.fiscaluno.login.AuthenticationBody
import com.fiscaluno.login.AuthenticationResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface FiscalunoApi {

    @Headers("X-Client-ID: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNsaWVudCI6ImFueSJ9LCJpc3MiOiJtdSJ9.k7uGe0qbuwwxAp_UOVGVft4eAJwDc_FnaZg-pmwzUZ0")
    @POST("https://fiscaluno-mu.herokuapp.com/users")
    fun authenticate(@Body authenticationBody: AuthenticationBody): Observable<Response<AuthenticationResponse>>

    @GET("https://fiscaluno-hyoga.herokuapp.com/")
    fun findInstitutions(): Observable<Response<List<Institution>>>

    @GET("https://fiscaluno-hyoga.herokuapp.com/{id}")
    fun findInstitutionsById(@Path("id") id: String): Observable<Response<Institution>>
}