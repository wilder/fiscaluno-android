package com.fiscaluno.network

import com.fiscaluno.repository.UserRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val userRepository: UserRepository) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val shouldAuthenticate = !request.headers().values("@").contains("NoAuth")

        val requestBuilder = request.newBuilder()
        requestBuilder
                .removeHeader("@")
                .addHeader("X-Client-ID", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImNsaWVudCI6ImFueSJ9LCJpc3MiOiJtdSJ9.k7uGe0qbuwwxAp_UOVGVft4eAJwDc_FnaZg-pmwzUZ0")

        if (shouldAuthenticate) {
            requestBuilder.addHeader("X-User-Token", userRepository.getUserToken())
        }

        return chain.proceed(requestBuilder.build())
    }

}
