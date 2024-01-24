package com.example.commerce.presentation.ui.profile.address

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentAddressBinding
import com.example.commerce.presentation.ui.items.ItemsViewModel
import com.example.commerce.presentation.ui.profile.ProfileViewModel
import com.example.commerce.presentation.ui.profile.address.adapter.AddressAdapter

class AddressFragment : BaseFragment<FragmentAddressBinding>(FragmentAddressBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private val addressAdapter by lazy { AddressAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner) {
            profileViewModel.getAddressUser(it.id)

        }

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addressRcv.gone()

        profileViewModel.addressData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    addressAdapter.list = it.data!!
                    with(binding) {
                        addressRcv.adapter = addressAdapter
                        addressRcv.vertical(requireContext())
                        pb.gone()
                        addressRcv.visible()
                    }
                }

                else -> {
                    binding.pb.visible()
                    binding.addressRcv.gone()
                }
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}