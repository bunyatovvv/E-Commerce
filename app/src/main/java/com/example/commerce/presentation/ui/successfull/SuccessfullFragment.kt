package com.example.commerce.presentation.ui.successfull

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.databinding.FragmentSuccessfullBinding

class SuccessfullFragment : BaseFragment<FragmentSuccessfullBinding>(FragmentSuccessfullBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.seeDetailsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_successfullFragment_to_orderFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}