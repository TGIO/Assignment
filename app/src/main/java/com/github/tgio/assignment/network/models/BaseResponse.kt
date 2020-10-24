package com.github.tgio.assignment.network.models

class BaseResponse<T>(
    val status: Status,
    val data: T?,
    val error: ErrorResponse?
) {
    override fun toString(): String {
        return "BaseResponse(status='$status', data=$data, error=$error)"
    }

    enum class Status {
        success,
        error
    }
}