package com.github.tgio.assignment.network.repository

import com.github.tgio.assignment.network.ApiService
import com.github.tgio.assignment.network.components.IErrorConverter
import kotlinx.coroutines.CoroutineDispatcher

class ProfileRepository(
    private val apiService: ApiService,
    errorConverter: IErrorConverter,
    dispatcher: CoroutineDispatcher
): Repository(errorConverter, dispatcher) {

    fun getProfileDetails(id: String) = getData { apiService.getProfile(id) }

    fun getProfiles() = getData { apiService.getList() }

}