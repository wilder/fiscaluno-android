package com.fiscaluno.network

import com.fiscaluno.login.AuthenticationBody
import com.fiscaluno.login.AuthenticationResponse
import com.fiscaluno.model.*
import com.fiscaluno.rating.detailedReview.DetailedReviewBody
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface FiscalunoApi {

    @Headers("@: NoAuth")
    @POST("https://athena-bff.herokuapp.com/v1/auth")
    fun authenticate(@Body authenticationBody: AuthenticationBody): Observable<Response<AuthenticationResponse>>

    @POST("https://athena-bff.herokuapp.com/v1/users")
    fun createOrUpdateUser(@Body user: Student): Observable<Response<AuthenticationResponse>>

    @GET("https://private-f15175-fiscalunoapi.apiary-mock.com/reviews/details/average")
    fun getDetailedReviews(
            @Query("institution_id") institutionId: Int,
            @Query("course") course: Int? = null,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5
    ): Observable<Response<StandardApiResponse<List<DetailedReview>>>>

    @GET("https://athena-bff.herokuapp.com/v1/reviews")
    fun getGeneralReviews(
            @Query("institution_id") institutionId: Int,
            @Query("course") course: Int? = null,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5
    ): Observable<Response<StandardApiResponse<List<GeneralReview>>>>

    @POST("https://athena-bff.herokuapp.com/v1/reviews")
    fun postGeneralReview(@Body generalReview: GeneralReview):
            Observable<Response<StandardApiResponse<GeneralReview>>>

    @POST("https://athena-bff.herokuapp.com/v1/reviews/{reviewId}/details")
    fun postDetailedReview(@Path("reviewId") id: Int, @Body detailedReview: DetailedReviewBody):
            Observable<Response<Any?>>

    @GET("https://athena-bff.herokuapp.com/v1/institutions")
    fun findInstitutions(
            @Query("name") name: String? = null,
            @Query("city") city: String? = null,
            @Query("state") state: String? = null,
            @Query("rate") rate: Float? = 0f,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5,
            @Query("sort") sortBy: String? = null): Observable<Response<StandardApiResponse<List<Institution>>>>

    @GET("https://athena-bff.herokuapp.com/v1/institutions/{id}")
    fun findInstitutionsById(@Path("id") id: String): Observable<Response<StandardApiResponse<Institution>>>

    @GET("https://athena-bff.herokuapp.com/v1/courses")
    fun findCourses(
            @Query("name") name: String? = null,
            @Query("institution_name") institutionName: String? = null,
            @Query("institution_id") institutionId: Int? = null,
            @Query("city") city: String? = null,
            @Query("state") state: String? = null,
            @Query("rate") rate: Float? = 0f,
            @Query("page") page: Int = 0,
            @Query("size") pageSize: Int = 5,
            @Query("sort") sortBy: String? = null): Observable<Response<StandardApiResponse<List<Course>>>>

}