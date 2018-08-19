package com.fiscaluno.network

import com.fiscaluno.model.Course
import com.fiscaluno.serializers.CourseSerializer
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHolder {
    operator fun invoke(baseUrl: String, authInterceptor: AuthInterceptor): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .client(okHttpClient(logger(), authInterceptor))
                .build()
    }

    private fun okHttpClient(interceptor: Interceptor, authInterceptor: AuthInterceptor) = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        addInterceptor(authInterceptor)
        build()
    }

    private fun logger() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private fun gson() =  GsonBuilder().run {
        registerTypeAdapter(Course::class.java, CourseSerializer())
                .create()
    }
}
