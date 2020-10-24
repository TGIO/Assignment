package com.github.tgio.assignment.base

import androidx.lifecycle.ViewModel
import com.github.tgio.assignment.misc.MutableStatefulLiveData

open class BaseViewModel<SM : ScreenModel> : ViewModel() {
    val state = MutableStatefulLiveData<SM>()
}