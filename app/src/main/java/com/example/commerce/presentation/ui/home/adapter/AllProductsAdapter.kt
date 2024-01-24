package com.example.commerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.ItemsRowBinding

class AllProductsAdapter :
    RecyclerView.Adapter<AllProductsAdapter.ProductsHolder>() {

    inner class ProductsHolder(val binding: ItemsRowBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
        val binding = ItemsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHolder(binding)
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
        val item = product[position]

        with(holder.binding) {
            productPrice.text = item.price.toString()
            productTitle.text = item.title
            productImage.setImageURL(item.thumbnail, root.context)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(item)
            }
        }
    }

    private var onItemClickListener: ((ProductDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProductDTO) -> Unit) {
        onItemClickListener = listener
    }
}