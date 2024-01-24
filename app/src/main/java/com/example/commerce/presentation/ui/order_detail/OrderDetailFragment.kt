package com.example.commerce.presentation.ui.order_detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.util.vertical
import com.example.commerce.databinding.FragmentOrderDetailBinding
import com.example.commerce.presentation.ui.order_detail.adapter.OrderDetailAdapter

class OrderDetailFragment :
    BaseFragment<FragmentOrderDetailBinding>(FragmentOrderDetailBinding::inflate) {

    private val args: OrderDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val order = args.order
        val adapter = OrderDetailAdapter(order.products)

        with(binding) {
            orderRcv.adapter = adapter
            orderRcv.vertical(requireContext())
            title.text = "Order ${order.id} Items"
            backCardview.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}