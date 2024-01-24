package com.example.commerce.presentation.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.vertical
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentCategoriesBinding
import com.example.commerce.presentation.ui.categories.adapter.CategoryAdapter
import com.example.commerce.presentation.ui.items.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    private val categoriesViewModel: CategotiesViewModel by viewModels()
    private lateinit var itemsViewModel: ItemsViewModel
    private val categoryAdapter by lazy { CategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        itemsViewModel = ViewModelProvider(requireActivity())[ItemsViewModel::class.java]

        categoriesViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    categoryAdapter.category = it.data!!
                    with(binding){
                        ctgRcv.adapter = categoryAdapter
                        ctgRcv.vertical(requireContext())
                        binding.pb.gone()
                    }
                    categoryAdapter.setOnItemClickListener {
                        itemsViewModel.getProductsByCategory(it.id)
                        findNavController().navigate(R.id.action_categoriesFragment_to_itemsFragment)
                    }
                }

                Status.ERROR -> {
                    binding.pb.gone()
                }

                Status.LOADING -> {
                    binding.pb.visible()
                }
            }
        })

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }
}