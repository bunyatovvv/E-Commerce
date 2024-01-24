package com.example.commerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.ItemsRowBinding

class AllProductsAdapter(val list: List<ProductDTO>) :
    RecyclerView.Adapter<AllProductsAdapter.ProductsHolder>() {

    inner class ProductsHolder(val binding: ItemsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val item = list[position]

        with(holder) {
            binding.productPrice.text = item.price.toString()
            binding.productTitle.text = item.title
            binding.productImage.setImageURL(item.thumbnail, binding.root.context)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    private var onItemClickListener: ((ProductDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductDTO) -> Unit) {
        onItemClickListener = listener
    }
}