package com.github.tgio.assignment.di

import android.util.Base64
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.github.tgio.assignment.Constants
import com.github.tgio.assignment.network.ApiService
import com.github.tgio.assignment.network.repository.ProfileRepository
import com.github.tgio.assignment.network.components.AuthInterceptor
import com.github.tgio.assignment.network.components.ErrorConverter
import com.github.tgio.assignment.network.components.IErrorConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {
    single {
        Dispatchers.IO
    }
    single {
        OkHttpClient.Builder()
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(
                AuthInterceptor(
                    get(named(Constants.NAME_TOKEN))
                )
            )
            .callTimeout(Constants.DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectTimeout(Constants.DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()
    }
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single<IErrorConverter> {
        ErrorConverter(get<Retrofit>())
    }
    single {
        JWT.create()
            .withClaim(Constants.PARAM_UID, Constants.UID)
            .withClaim(Constants.PARAM_IDENTITY, Constants.IDENTITY)
    }
    single {
        val secret = Base64.encodeToString(Constants.SECRET.toByteArray(), Base64.NO_WRAP)
        Algorithm.HMAC256(secret)
    }
    single(named(Constants.NAME_TOKEN)) {
        get<JWTCreator.Builder>().sign(get<Algorithm>())
    }
    single {
        ProfileRepository(
            apiService = get(),
            errorConverter = get(),
            dispatcher = get()
        )
    }
}