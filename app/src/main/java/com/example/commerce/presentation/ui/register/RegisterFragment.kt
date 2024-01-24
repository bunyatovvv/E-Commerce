package com.example.commerce.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.databinding.FragmentRegisterBinding
import com.example.commerce.domain.model.RegisterModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerViewModel =
            ViewModelProvider(requireActivity())[RegisterViewModel::class.java]

        binding.continueBtn.setOnClickListener {
            val registerModel = RegisterModel(
                binding.emailEt.text.toString(),
                binding.firstnameEt.text.toString(),
                binding.passwordEt.text.toString(),
                binding.surnameEt.text.toString()
            )
            registerViewModel.register(registerModel)
        }

        registerViewModel.authToken.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(R.id.action_fragmentRegister_to_homeFragment)
            }
        })

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}