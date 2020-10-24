package com.github.tgio.assignment.network.models

class ErrorResponse(
    val error: ErrorMessage?
) {
    override fun toString(): String {
        return "ErrorResponse(error=$error)"
    }
}

class ErrorMessage(
    val message: String
) {
    override fun toString(): String {
        return "ErrorMessage(message='$message')"
    }
}