package com.example.commerce.presentation.ui.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.databinding.SingleCategoryBinding
import com.example.commerce.domain.model.BasketModel

class CategoryAdapter :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    inner class CategoryHolder(val binding: SingleCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<CategoriesDTO>() {
        override fun areItemsTheSame(oldItem: CategoriesDTO, newItem: CategoriesDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoriesDTO, newItem: CategoriesDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    var category: List<CategoriesDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding =
            SingleCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item = category[position]

        with(holder) {
            binding.titleCategoryTxt.text = item.name
            binding.imageCategory.setImageURL(item.thumbnail, holder.binding.root.context)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    private var onItemClickListener: ((CategoriesDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoriesDTO) -> Unit) {
        onItemClickListener = listener
    }
}