package com.example.commerce.presentation.ui.items.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.ProductsRowBinding

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemsHolder>() {

    inner class ItemsHolder(val binding: ProductsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ProductDTO>() {
        override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    var product: List<ProductDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val binding = ProductsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsHolder(binding)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = product[position]

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