package com.example.commerce.presentation.ui.items

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.common.util.Status
import com.example.commerce.common.util.gone
import com.example.commerce.common.util.visible
import com.example.commerce.databinding.FragmentItemsBinding
import com.example.commerce.presentation.ui.items.adapter.ItemsAdapter
import com.example.commerce.presentation.ui.single_product.SingleProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : BaseFragment<FragmentItemsBinding>(FragmentItemsBinding::inflate) {

    private lateinit var itemsViewModel: ItemsViewModel
    private lateinit var singleProductViewModel: SingleProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        itemsViewModel = ViewModelProvider(requireActivity())[ItemsViewModel::class.java]
        singleProductViewModel =
            ViewModelProvider(requireActivity())[SingleProductViewModel::class.java]

        itemsViewModel.productData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val adapter = ItemsAdapter(it.data !!)
                    binding.itemsRcv.adapter = adapter
                    binding.itemsRcv.layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter.setOnItemClickListener {
                        singleProductViewModel.getProductsById(it.id)
                        singleProductViewModel.getProductComments(it.id)
                        findNavController().navigate(R.id.action_itemsFragment_to_singleProductFragment)
                    }
                    binding.pb.gone()
                }

                else -> {
                    with(binding) {
                        rootlayout.visible()
                        pb.visible()
                    }
                }
            }
        })

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }
}