package com.example.commerce.presentation.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSigninBinding>(FragmentSigninBinding::inflate) {

    private lateinit var signInViewModel: SingInViewModel
    private lateinit var baseViewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signInViewModel =
            ViewModelProvider(requireActivity())[SingInViewModel::class.java]

        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }

        baseViewModel.userData.observe(
            viewLifecycleOwner
        ) { user ->
            when (user.status) {
                Status.SUCCESS ->
                    binding.continueBtn.setOnClickListener {
                        user.data?.let {
                            it.map {
                                if (binding.emailEt.text.toString() == it.email) {
                                    baseViewModel.email.postValue(it.email)
                                    findNavController().navigate(R.id.action_signInFragment_to_singnInPasswordFragment)
                                }
                            }
                        }
                    }

                Status.ERROR -> {
                    binding.continueBtn.setOnClickListener {
                        Log.e("userrrrr", user.message.toString())
                    }
                }

                Status.LOADING -> {
                    binding.continueBtn.setOnClickListener {
                    }
                }
            }
        }

        binding.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_fragmentRegister)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}