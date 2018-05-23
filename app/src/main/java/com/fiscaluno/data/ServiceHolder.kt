package com.fiscaluno.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHolder {
    operator fun invoke(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpCliente(logger()))
                .build()
    }

    private fun okHttpCliente(interceptor: Interceptor) = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }

    private fun logger() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}
