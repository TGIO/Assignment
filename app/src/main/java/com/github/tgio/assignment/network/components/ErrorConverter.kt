package com.github.tgio.assignment.network.components

import com.github.tgio.assignment.network.models.APIError
import com.github.tgio.assignment.network.models.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

class ErrorConverter(
    retrofit: Retrofit
): IErrorConverter {
    private val internalConverter: Converter<ResponseBody, ErrorResponse> =
        retrofit.responseBodyConverter(
            ErrorResponse::class.java, emptyArray()
        )

    override fun convert(errorBody: ResponseBody?): APIError {
        val message = errorBody?.let {
            internalConverter.convert(it)?.error?.message
        }
        return APIError("Error: ${message ?: "Unknown"}")
    }
}