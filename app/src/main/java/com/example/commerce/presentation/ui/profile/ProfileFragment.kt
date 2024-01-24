package com.example.commerce.presentation.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.util.setImageURL
import com.example.commerce.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel =
            ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        binding.signOut.setOnClickListener {
            profileViewModel.logout()
        }

        profileViewModel.userData.observe(viewLifecycleOwner, Observer {
            it?.let {
                with(binding) {
                    emailTxt.text = it.email
                    nameSurnameTxt.text = "${it.name} ${it.surname}"
                    ppImage.setImageURL(it.photo, requireContext())
                }
            }
            if (it == null) {
                findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
            }
        })

        with(binding) {
            addressCv.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_addressFragment)
            }
            wishlistCv.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_wishlistFragment)
            }
            paymentCv.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_paymentFragment)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}