package com.github.tgio.assignment.network.repository

import androidx.lifecycle.liveData
import com.github.tgio.assignment.network.components.IErrorConverter
import com.github.tgio.assignment.network.models.BaseResponse
import com.github.tgio.assignment.ui.StatefulData
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

open class Repository(
    private val errorConverter: IErrorConverter,
    private val dispatcher: CoroutineDispatcher
) {

    fun <T> getData(call: suspend () -> Response<BaseResponse<T>>) =
        liveData<StatefulData<T>>(dispatcher) {
            emit(StatefulData.Loading())
            try {
                val response = call.invoke()
                if (response.isSuccessful && response.body()?.data != null) {
                    emit(StatefulData.Success(response.body()!!.data!!))
                } else {
                    val apiError = errorConverter.convert(response.errorBody())
                    emit(StatefulData.Error(apiError))
                }
            } catch (e: Exception) {
                emit(StatefulData.Error(e))
            }
        }
}