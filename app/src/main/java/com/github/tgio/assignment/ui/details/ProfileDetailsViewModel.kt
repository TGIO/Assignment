package com.github.tgio.assignment.ui.details

import com.github.tgio.assignment.base.BaseViewModel
import com.github.tgio.assignment.network.repository.ProfileRepository
import com.github.tgio.assignment.ui.StatefulData

class ProfileDetailsViewModel(
    private val repository: ProfileRepository
) : BaseViewModel<ProfileDetailsSM>() {

    fun getProfileDetails(id: String) {
        state.putLoading()
        repository.getProfileDetails(id).observeForever {
            when (it) {
                is StatefulData.Success -> {
                    state.putData(ProfileDetailsSM(it.data))
                }
                is StatefulData.Error -> state.putError(it.throwable)
            }
        }
    }
}
