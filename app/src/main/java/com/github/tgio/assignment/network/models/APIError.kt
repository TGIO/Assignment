package com.github.tgio.assignment.network.models

data class APIError(
    override val message: String
) : Throwable(message) {
    override fun toString(): String {
        return "APIError(message=$message)"
    }
}