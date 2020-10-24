package com.github.tgio.assignment.misc

import androidx.lifecycle.MutableLiveData
import com.github.tgio.assignment.ui.StatefulData

class MutableStatefulLiveData<T> : MutableLiveData<StatefulData<T>>() {
    fun putLoading(loadingData: Any? = null) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Loading(loadingData)
        } else {
            this.postValue(StatefulData.Loading(loadingData))
        }
    }

    fun putData(data: T) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Success(data)
        } else {
            this.postValue(StatefulData.Success(data))
        }
    }

    fun putError(error: Throwable) {
        if (LooperChecker.isMainThread()) {
            this.value = StatefulData.Error(error)
        } else {
            this.postValue(StatefulData.Error(error))
        }
    }
}