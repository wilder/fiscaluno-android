package com.fiscaluno.network

import com.fiscaluno.login.AuthenticationBody
import com.fiscaluno.login.AuthenticationResponse
import com.fiscaluno.model.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface FiscalunoApi {

    @Headers("@: NoAuth")
    @POST("https://fiscaluno-mu.herokuapp.com/users")
    fun authenticate(@Body authenticationBody: AuthenticationBody): Observable<Response<AuthenticationResponse>>

    @GET("institutions/{institutionId}/reviews/details/average")
    fun getInstitutionsDetailedReviewsAverage(@Path("institutionId") institutionId: String): Observable<Response<StandardApiResponse<List<DetailedReview>>>>

    @GET("institutions/{institutionId}/reviews")
    fun getInstitutionsGeneralReviewsAverage(@Path("institutionId") institutionId: String): Observable<Response<StandardApiResponse<List<GeneralReview>>>>


    @GET("institutions")
    fun findInstitutions(
            @Query("name") name: String? = null,
            @Query("city") city: String? = null,
            @Query("state") state: String? = null,
            @Query("rate") rate: Float? = 0f,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5,
            @Query("sort") sortBy: String? = null): Observable<Response<StandardApiResponse<List<Institution>>>>

    @GET("institutions/{id}")
    fun findInstitutionsById(@Path("id") id: String): Observable<Response<StandardApiResponse<Institution>>>

    @GET("courses")
    fun findCourses(
            @Query("name") name: String? = null,
            @Query("institutionName") institutionName: String? = null,
            @Query("institutionId") institutionId: String? = null,
            @Query("city") city: String? = null,
            @Query("state") state: String? = null,
            @Query("rate") rate: Float? = 0f,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5,
            @Query("sort") sortBy: String? = null): Observable<Response<StandardApiResponse<List<Course>>>>

}