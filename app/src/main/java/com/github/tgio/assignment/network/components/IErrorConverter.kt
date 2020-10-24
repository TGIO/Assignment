package com.github.tgio.assignment.network.components

import com.github.tgio.assignment.network.models.APIError
import okhttp3.ResponseBody

interface IErrorConverter {
    fun convert(errorBody: ResponseBody?): APIError
}