package com.example.commerce.presentation.ui.checkout

import android.annotation.SuppressLint
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
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentCheckoutBinding
import com.example.commerce.domain.model.OrderModel
import com.example.commerce.presentation.ui.basket.BasketViewModel
import com.example.commerce.presentation.ui.profile.add_address.AddAddressFragment
import com.example.commerce.presentation.ui.profile.add_card.AddCardFragment
import kotlin.properties.Delegates

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>(FragmentCheckoutBinding::inflate) {

    private lateinit var checkoutViewModel: CheckoutViewModel
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var basketViewModel: BasketViewModel
    private var address = ""
    private var payment = ""
    private var addressSuccess = false
    private var paymentSuccess = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkoutViewModel =
            ViewModelProvider(requireActivity())[CheckoutViewModel::class.java]
        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        basketViewModel =
            ViewModelProvider(requireActivity())[BasketViewModel::class.java]

        baseViewModel.user.observe(viewLifecycleOwner, Observer {
            checkoutViewModel.getAddressUser(it.id)
            checkoutViewModel.getPaymentUser(it.id)
        })
        binding.pb.visible()
        binding.shippingaddress.text = ""
        binding.paymentMethod.text = ""

        checkoutViewModel.paymentData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    paymentSuccess = true
                    if (it.data !!.isNotEmpty()) {
                        it.data.last().apply {
                            binding.paymentMethod.text = "${this.number}}"
                        }
                    } else {
                        binding.paymentMethod.setText(R.string.add_payment_method)
                        binding.paymentCardView.setOnClickListener {
                            checkoutViewModel.infoPayment.postValue("checkout")
                            findNavController().navigate(R.id.action_checkoutFragment_to_addCardFragment)
                        }
                    }
                }

                Status.LOADING -> {
                    binding.pb.visible()
                    binding.root.gone()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        checkoutViewModel.addressData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    addressSuccess = true
                    Log.e("testttttttt",it.data.toString())
                    if (it.data !!.isNotEmpty()) {
                        it.data.last().apply {
                            address = "${this.zip} ${this.street_address}, ${this.state} ${this.city}"
                            binding.shippingaddress.text = "${this.zip} ${this.street_address}, ${this.state} ${this.city}"
                        }
                    } else {
                        binding.shippingaddress.setText(R.string.add_shipping_address)
                        binding.shippingAddressCardView.setOnClickListener {
                            checkoutViewModel.infoAddress.postValue("checkout")
                            findNavController().navigate(R.id.action_checkoutFragment_to_addAddressFragment)
                        }
                    }
                }

                Status.LOADING -> {
                    binding.pb.visible()
                    binding.root.gone()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
            if (addressSuccess && paymentSuccess) {
                binding.root.visible()
                binding.pb.gone()
            }
        }

        basketViewModel.checkOutModel.observe(viewLifecycleOwner, Observer {
            with(binding) {
                subtotalTxt.text = "$ ${it.subtotal}"
                taxTxt.text = "$ 0"
                shippingCostTxt.text = "$ ${it.shipping}"
                totalPriceTxt.text = "$ ${it.total}"
            }
        })

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        basketViewModel.basketData.observe(viewLifecycleOwner, Observer { basket ->
            if (basket.status == Status.SUCCESS) {
                binding.checkOutButton.setOnClickListener {
                    val orderModel =
                        OrderModel(address, baseViewModel.user.value !!.id, basket.data !!.products)
                    checkoutViewModel.addOrder(orderModel)
                    findNavController().navigate(R.id.action_checkoutFragment_to_successfullFragment)
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}