package com.example.commerce.presentation.ui.profile.payment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentPaymentBinding
import com.example.commerce.presentation.ui.profile.ProfileViewModel
import com.example.commerce.presentation.ui.profile.payment.adapter.PaymentAdapter

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private val paymentAdapter by lazy { PaymentAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner) {
            profileViewModel.getPaymentUser(it.id)
        }

        binding.paymentRcv.gone()

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        profileViewModel.paymentData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                paymentAdapter.list = it.data!!
                with(binding) {
                    paymentRcv.adapter = paymentAdapter
                    paymentRcv.vertical(requireContext())
                    pb.gone()
                    paymentRcv.visible()
                }
            } else {
                binding.pb.visible()
                binding.paymentRcv.gone()
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_addCardFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

}