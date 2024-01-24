package com.example.commerce.presentation.ui.profile.add_address

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.databinding.FragmentAddAddressBinding
import com.example.commerce.domain.model.AddressModel
import com.example.commerce.presentation.ui.checkout.CheckoutViewModel
import com.example.commerce.presentation.ui.profile.ProfileViewModel
import com.example.commerce.presentation.ui.profile.address.AddressFragmentDirections

class AddAddressFragment :
    BaseFragment<FragmentAddAddressBinding>(FragmentAddAddressBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var checkoutViewModel: CheckoutViewModel
    private var currentUser = 0
    private var info = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        checkoutViewModel =
            ViewModelProvider(requireActivity())[CheckoutViewModel::class.java]

        checkoutViewModel.infoAddress.observe(viewLifecycleOwner){
            info = it
        }

        binding.saveButton.setOnClickListener {
            val city = binding.cityText.text.toString()
            val state = binding.stateText.text.toString()
            val street = binding.streetAddress.text.toString()
            val zip = binding.zipCode.text.toString()
            if (! validation()){
                val addressModel =
                    AddressModel(city, state, street, baseViewModel.user.value !!.id, zip)
                profileViewModel.addAddress(addressModel)

            }
        }

        profileViewModel.singleAddress.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                Toast.makeText(requireContext(), R.string.succesfully_added, Toast.LENGTH_LONG)
                    .show()
                if (info == "checkout"){
                    findNavController().navigate(R.id.action_addAddressFragment_to_checkoutFragment)
                } else {
                    findNavController().popBackStack()
                }
            }
            super.onViewCreated(view, savedInstanceState)
        }
    }

    private fun validation(): Boolean {
        return with(binding) {
            streetAddress.text.isNullOrEmpty() ||
                    cityText.text.isNullOrEmpty() ||
                    stateText.text.isNullOrEmpty() ||
                    zipCode.text.isNullOrEmpty()
        }
    }
}