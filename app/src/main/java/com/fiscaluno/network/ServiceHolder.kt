package com.fiscaluno.network

import com.fiscaluno.model.Course
import com.fiscaluno.serializers.CourseSerializer
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

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
        registerTypeAdapter(Date::class.java,
                JsonDeserializer<Date> { json, _, _ -> Date(json?.asJsonPrimitive!!.asLong) })
                .create()
    }
}
