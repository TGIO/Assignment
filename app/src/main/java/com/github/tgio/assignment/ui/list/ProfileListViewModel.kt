package com.github.tgio.assignment.ui.list

import com.github.tgio.assignment.base.BaseViewModel
import com.github.tgio.assignment.network.repository.ProfileRepository
import com.github.tgio.assignment.ui.StatefulData

class ProfileListViewModel(
    private val repository: ProfileRepository
) : BaseViewModel<ProfilesListSM>() {

    fun getProfilesList() {
        // For current scope, we don't need to load new list every time view is re-created
        if (state.value == null) {
            state.putLoading()
            repository.getProfiles().observeForever {
                when (it) {
                    is StatefulData.Success -> {
                        state.putData(ProfilesListSM(it.data))
                    }
                    is StatefulData.Error -> state.putError(it.throwable)
                }
            }
        }
    }
}