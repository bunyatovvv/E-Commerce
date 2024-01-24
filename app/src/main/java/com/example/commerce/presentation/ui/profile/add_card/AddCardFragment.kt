package com.example.commerce.presentation.ui.profile.add_card

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.databinding.FragmentAddCardBinding
import com.example.commerce.domain.model.PaymentModel
import com.example.commerce.presentation.ui.checkout.CheckoutViewModel
import com.example.commerce.presentation.ui.profile.ProfileViewModel

class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private var currentUser = 0
    private lateinit var checkoutViewModel: CheckoutViewModel
    private var info = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        checkoutViewModel =
            ViewModelProvider(requireActivity())[CheckoutViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner, Observer {
            currentUser = it.id
        })

        checkoutViewModel.infoPayment.observe(viewLifecycleOwner){
           info = it
        }

        binding.saveButton.setOnClickListener {
            if (!validation()){
                val cardNumber = binding.cardNumberEt.text.toString()
                val ccv = binding.ccvEt.text.toString()
                val exp = binding.expEt.text.toString()
                val holder = binding.cardholderEt.text.toString()
                val paymentModel = PaymentModel(cardNumber, ccv, exp, holder, baseViewModel.user.value!!.id )
                profileViewModel.addPaymentUser(paymentModel)
            } else {
                Toast.makeText(requireContext(), R.string.fill_fields, Toast.LENGTH_LONG).show()
            }
        }

        profileViewModel.paymentPost.observe(viewLifecycleOwner){
            if (it.status == Status.SUCCESS){
                Toast.makeText(requireContext(), R.string.succesfully_added, Toast.LENGTH_LONG)
                    .show()
                if (info == "checkout"){
                    findNavController().navigate(R.id.action_addCardFragment_to_checkoutFragment)
                } else {
                    findNavController().popBackStack()
                }
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun validation(): Boolean {
        return with(binding) {
            cardNumberEt.text.isNullOrEmpty() ||
                    ccvEt.text.isNullOrEmpty() ||
                    expEt.text.isNullOrEmpty() ||
                    cardholderEt.text.isNullOrEmpty()
        }
    }
}