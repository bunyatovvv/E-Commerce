package com.example.commerce.presentation.ui.signin.signinpassword

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentSinginpasswordBinding
import com.example.commerce.domain.model.LoginModel
import com.example.commerce.presentation.ui.signin.SingInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingnInPasswordFragment :
    BaseFragment<FragmentSinginpasswordBinding>(FragmentSinginpasswordBinding::inflate) {

    private lateinit var signInViewModel: SingInViewModel
    private lateinit var baseViewModel: BaseViewModel
    var email = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signInViewModel =
            ViewModelProvider(requireActivity())[SingInViewModel::class.java]

        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]


        baseViewModel.email.observe(viewLifecycleOwner, Observer {
            email = it
        })

        signInViewModel.token.observe(viewLifecycleOwner, Observer { auth ->
            if (auth.authToken.isNotEmpty()) {
                findNavController().navigate(R.id.action_singnInPasswordFragment_to_homeFragment)
            }
        })

        binding.continueBtn.setOnClickListener {
            binding.pb.visible()
            signInViewModel.login(LoginModel(email, binding.passwordEt.text.toString()))
        }

        super.onViewCreated(view, savedInstanceState)
    }
}