package com.example.commerce.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.BaseViewModel
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.linear
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentHomeBinding
import com.example.commerce.presentation.ui.home.adapter.AllProductsAdapter
import com.example.commerce.presentation.ui.home.adapter.CategoiresAdapter
import com.example.commerce.presentation.ui.single_product.SingleProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var baseViewModel: BaseViewModel

    private lateinit var singleProductViewModel: SingleProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        singleProductViewModel =
            ViewModelProvider(requireActivity())[SingleProductViewModel::class.java]
        baseViewModel =
            ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        baseViewModel.getCurrentUser()

        homeViewModel.apply {
            productData.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        var filter = it.data !!.filter { it.rating > 4 }
                        with(binding) {
                            val adapter = AllProductsAdapter(it.data)
                            topSellingRcv.adapter = adapter
                            topSellingRcv.layoutManager?.linear(requireContext())
                            progressBar.gone()
                            adapter.setOnItemClickListener {
                                singleProductViewModel.getProductsById(it.id)
                                singleProductViewModel.getProductComments(it.id)
                                findNavController().navigate(R.id.action_homeFragment_to_singleProductFragment)
                            }
                        }

                        filter = it.data.filter { it.price < 130.9 }
                        val adapter = AllProductsAdapter(filter)
                        binding.newInRcv.adapter = adapter
                        binding.newInRcv.layoutManager?.linear(requireContext())
                        adapter.setOnItemClickListener {
                            singleProductViewModel.getProductsById(it.id)
                            findNavController().navigate(R.id.action_homeFragment_to_singleProductFragment)
                        }
                    }

                    Status.LOADING -> {
                        binding.progressBar.visible()
                    }

                    Status.ERROR -> {
                        binding.progressBar.gone()
                    }
                }
            })
            categoryData.observe(viewLifecycleOwner, Observer {
                if (it.status == Status.SUCCESS) {
                    binding.categoriesRcv.adapter = CategoiresAdapter(it.data !!)
                    binding.categoriesRcv.layoutManager?.linear(requireContext())
                }
            })
        }

        binding.seeAllCategoriesTxt.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_categoriesFragment)
        }

        binding.basketCardView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
        }


        super.onViewCreated(view, savedInstanceState)
    }
}