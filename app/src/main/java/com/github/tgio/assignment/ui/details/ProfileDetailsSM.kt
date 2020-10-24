package com.github.tgio.assignment.ui.details

import com.github.tgio.assignment.base.ScreenModel
import com.github.tgio.assignment.network.models.Profile

class ProfileDetailsSM(
    val profile: Profile
) : ScreenModel {
    override fun toString(): String {
        return "ProfileDetailsSM(profile=$profile)"
    }
}