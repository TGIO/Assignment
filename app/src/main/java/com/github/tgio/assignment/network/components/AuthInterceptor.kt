package com.github.tgio.assignment.network.components

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val token: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            )
        } catch (e: Exception) {
            chain.proceed(chain.request())
        }
    }
}