package com.example.commerce.presentation.ui.order

import android.os.Bundle
import android.provider.ContactsContract.StatusUpdates
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.FtsOptions
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Resource
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentOrderBinding
import com.example.commerce.presentation.ui.order.adapter.OrdersAdapter

class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var orderViewModel: OrderViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        orderViewModel =
            ViewModelProvider(requireActivity())[OrderViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner, Observer {
            orderViewModel.getOrdersUser(it.id)
        })

        orderViewModel.orderData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val adapter = OrdersAdapter(it.data !!)
                    with(binding) {
                        pb.gone()
                        infoText.gone()
                        ordersRcv.adapter = adapter
                        ordersRcv.vertical(requireContext())
                        ordersRcv.visible()
                    }

                    adapter.setOnItemClickListener {
                        val action =
                            OrderFragmentDirections.actionOrderFragmentToSingleOrderFragment(it)
                        findNavController().navigate(action)
                    }
                    if (it.data.isEmpty()) {
                        binding.pb.gone()
                        binding.infoText.visible()
                    }
                }

                Status.ERROR -> {
                    binding.ordersRcv.gone()
                    binding.pb.gone()
                }

                Status.LOADING -> {
                    binding.ordersRcv.gone()
                    binding.pb.visible()
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}