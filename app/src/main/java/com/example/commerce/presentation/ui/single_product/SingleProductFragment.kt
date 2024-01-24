package com.example.commerce.presentation.ui.single_product

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
import com.example.commerce.common.util.linear
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.data.dto.UserDTO
import com.example.commerce.data.mapper.toFavoritesDTO
import com.example.commerce.databinding.FragmentSingleProductBinding
import com.example.commerce.domain.model.BasketModel
import com.example.commerce.domain.model.BasketPostModel
import com.example.commerce.presentation.ui.basket.BasketViewModel
import com.example.commerce.presentation.ui.single_product.adapter.CommentsAdapter
import com.example.commerce.presentation.ui.single_product.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log
import kotlin.math.round

@AndroidEntryPoint
class SingleProductFragment :
    BaseFragment<FragmentSingleProductBinding>(FragmentSingleProductBinding::inflate) {

    private lateinit var singleProductViewModel: SingleProductViewModel
    private lateinit var baseViewModel: BaseViewModel
    private lateinit var basketViewModel: BasketViewModel
    private var currentUserId = 0
    private var quantity = 1
    private var userList = listOf<UserDTO>()
    private val viewPagerAdapter by lazy { ViewPagerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        singleProductViewModel =
            ViewModelProvider(requireActivity())[SingleProductViewModel::class.java]
        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        basketViewModel = ViewModelProvider(requireActivity())[BasketViewModel::class.java]

        baseViewModel.getCurrentUser()

        binding.scview.gone()
        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeLiveData() {
        baseViewModel.userData.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                userList = it.data !!
            }
        }

        baseViewModel.user.observe(viewLifecycleOwner, Observer {
            currentUserId = it.id
        })



        singleProductViewModel.productData.observe(viewLifecycleOwner, Observer { productData ->
            when (productData.status) {
                Status.SUCCESS -> {
                    productData.data?.let { product ->
                        viewPagerAdapter.imageList = product.images

                        with(binding.imagesViewPager) {
                            binding.imagesViewPager.adapter = viewPagerAdapter
                            layoutManager?.linear(requireContext())
                            clipToPadding = false
                            clipChildren = false
                        }
                        with(binding) {
                            titleText.text = product.title
                            priceText.text = "$${product.price}"
                            descriptionTv.text = product.description
                            rating.text = product.rating.toString()
                            scview.visible()
                            pb.gone()
                            buyLayout.visible()
                            topLayout.visible()
                            totalPrice.text = "$${product.price}"
                            decreasecard.setOnClickListener {
                                if (binding.quantityTxt.text.toString().toInt() > 1) {
                                    quantity --
                                    binding.quantityTxt.text = quantity.toString()
                                    val total = round(quantity * product.price)
                                    binding.totalPrice.text = "$${total}"
                                }
                            }
                            addcard.setOnClickListener {
                                quantity ++
                                binding.quantityTxt.text = quantity.toString()
                                val total = round(quantity * product.price)
                                binding.totalPrice.text = "$${total}"
                            }
                            favCardview.setOnClickListener {
                                singleProductViewModel.insertFavorite(product.toFavoritesDTO())
                                Toast.makeText(
                                    requireContext(),
                                    R.string.product_added_fav,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            binding.addToBag.setOnClickListener {
                                val basketModel = BasketModel(
                                    product.thumbnail,
                                    product.price,
                                    product.id,
                                    quantity,
                                    product.title
                                )
                                val basketPostModel = BasketPostModel(basketModel, baseViewModel.user.value!!.id)
                                basketViewModel.postBasketUser(basketPostModel)
                                basketViewModel.addBasketData.observe(viewLifecycleOwner){
                                    if (it.status == Status.SUCCESS){
                                        pb.gone()
                                        findNavController().navigate(R.id.action_singleProductFragment_to_basketFragment)
                                    } else {
                                        pb.visible()
                                    }
                                }
                            }
                        }
                    }
                }

                Status.ERROR -> {
                    with(binding) {
                        pb.gone()
                        scview.visible()
                    }
                }

                else -> {
                    with(binding) {
                        scview.gone()
                        pb.visible()
                        buyLayout.gone()
                        topLayout.gone()
                    }
                }
            }

        })

        singleProductViewModel.commentsData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        val adapter = CommentsAdapter(it, userList)
                        with(binding) {
                            commentsRcv.adapter = adapter
                            commentsRcv.vertical(requireContext())
                        }
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {
                }
            }
        })
    }
}