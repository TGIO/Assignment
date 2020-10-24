package com.github.tgio.assignment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.tgio.assignment.ui.StatefulData

abstract class BaseFragment<T: ScreenModel> : Fragment {
    constructor(): super()
    constructor(layoutId: Int): super(layoutId)

    abstract val vm: BaseViewModel<T>

    abstract fun onLoading()
    abstract fun onSuccess(data: T)
    abstract fun onError(throwable: Throwable)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.state.observe(viewLifecycleOwner) {
            when (it) {
                is StatefulData.Success -> onSuccess(it.data)
                is StatefulData.Loading -> onLoading()
                is StatefulData.Error -> onError(it.throwable)
            }
        }
    }
}
