package com.github.tgio.assignment.di

import com.github.tgio.assignment.ui.details.ProfileDetailsViewModel
import com.github.tgio.assignment.ui.list.ProfileListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        ProfileListViewModel(
            repository = get()
        )
    }
    viewModel {
        ProfileDetailsViewModel(
            repository = get()
        )
    }
}