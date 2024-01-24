package com.example.commerce.presentation.ui.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentBasketBinding
import com.example.commerce.domain.model.CheckoutModel
import com.example.commerce.presentation.ui.basket.adapter.BasketAdapter
import kotlin.math.roundToInt

class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var basketViewModel: BasketViewModel
    private var sum = 0.0
    private var subtotal = 0
    private var shipping = 0
    private var total = 0
    private val basketAdapter by lazy { BasketAdapter() }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        basketViewModel = ViewModelProvider(requireActivity())[BasketViewModel::class.java]

        binding.emptyCart.gone()

        baseViewModel.user.observe(viewLifecycleOwner, Observer {
            basketViewModel.getBasketUser(it.id)
        })

        binding.basketRcv.gone()

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.checkOutButton.isEnabled = false

        basketViewModel.basketData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data !!.products.isNotEmpty()) {
                        basketAdapter.basket = it.data.products
                        with(binding) {
                            pb.gone()
                            basketRcv.visible()
                            basketRcv.adapter = basketAdapter
                            basketRcv.vertical(requireContext())
                            bottomLayout.visible()
                            removeAll.visible()
                            checkOutButton.visible()
                            checkOutButton.isEnabled = true
                        }

                        for (item in it.data.products) {
                            val result = item.price * item.quantity
                            sum += result
                            subtotal = sum.roundToInt()
                        }
                        shipping = it.data.products.size * 9
                        binding.subtotalTxt.text = "$ ${sum.roundToInt()}"
                        binding.shippingCostTxt.text = "$ ${shipping}"
                        binding.totalPriceTxt.text = "$ ${sum.roundToInt() + shipping}"
                        total = sum.roundToInt() + shipping
                        sum = 0.0
                    } else {
                        with(binding) {
                            pb.gone()
                            bottomLayout.gone()
                            removeAll.gone()
                            basketRcv.gone()
                            emptyCart.visible()
                            checkOutButton.gone()
                        }
                    }
                }
                Status.LOADING -> {
                    with(binding){
                        pb.visible()
                        basketRcv.gone()
                        emptyCart.gone()
                        checkOutButton.isEnabled = false
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.checkOutButton.setOnClickListener {
            val check = CheckoutModel(subtotal, shipping, 0, total)
            basketViewModel.checkOutModel.postValue(check)
            findNavController().navigate(R.id.action_basketFragment_to_checkoutFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}