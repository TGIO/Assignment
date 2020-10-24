package com.github.tgio.assignment.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tgio.assignment.R
import com.github.tgio.assignment.base.BaseFragment
import com.github.tgio.assignment.ext.gone
import com.github.tgio.assignment.ext.visible
import com.github.tgio.assignment.ui.details.ProfileDetailsFragmentArgs
import kotlinx.android.synthetic.main.fragment_profiles.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilesFragment : BaseFragment<ProfilesListSM>(R.layout.fragment_profiles),
    ProfileItemClickListener {

    override val vm: ProfileListViewModel by viewModel()
    private lateinit var profileItemRecyclerViewAdapter: MyProfileItemRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileItemRecyclerViewAdapter = MyProfileItemRecyclerViewAdapter(this)
        with(list) {
            layoutManager = LinearLayoutManager(context)
            adapter = profileItemRecyclerViewAdapter
        }
        vm.getProfilesList()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Profile List"
    }

    override fun onProfileClicked(profile: String) {
        findNavController().navigate(
            R.id.profileDetailsFragment,
            ProfileDetailsFragmentArgs(profile).toBundle()
        )
    }

    override fun onLoading() {
        textView.text = "Loading"
        textView.visible()
        list.gone()
    }

    override fun onError(throwable: Throwable) {
        textView.text = throwable.message
        textView.visible()
        list.gone()
    }

    override fun onSuccess(data: ProfilesListSM) {
        textView.gone()
        textView.text = ""
        profileItemRecyclerViewAdapter.setValues(data.profiles)
        list.visible()
    }

}