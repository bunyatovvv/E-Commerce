package com.example.commerce.presentation.ui.items.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.ProductsRowBinding

class ItemsAdapter(val list: List<ProductDTO>) : RecyclerView.Adapter<ItemsAdapter.ItemsHolder>() {

    inner class ItemsHolder(val binding: ProductsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val binding = ProductsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = list[position]

        with(holder) {
            binding.productTitle.text = item.title
            binding.productPrice.text = item.price.toString()
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