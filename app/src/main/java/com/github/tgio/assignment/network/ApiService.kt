package com.github.tgio.assignment.network

import com.github.tgio.assignment.network.models.BaseResponse
import com.github.tgio.assignment.network.models.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/list")
    suspend fun getList(): Response<BaseResponse<List<String>>>

    @GET("/get/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): Response<BaseResponse<Profile>>
}