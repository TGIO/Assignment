package com.github.tgio.assignment.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.github.tgio.assignment.R
import com.github.tgio.assignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileDetailsFragment : BaseFragment<ProfileDetailsSM>(R.layout.fragment_profile_details) {
    private val args by navArgs<ProfileDetailsFragmentArgs>()
    override val vm: ProfileDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getProfileDetails(args.profileId)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Profile Details"
    }

    override fun onSuccess(data: ProfileDetailsSM) {
        textView.text = data.profile.toString()
    }

    override fun onLoading() {
        textView.text = "Loading"
    }

    override fun onError(throwable: Throwable) {
        textView.text = throwable.message
    }
}