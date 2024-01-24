package com.example.commerce.presentation.ui.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.databinding.SingleCategoryBinding

class CategoryAdapter(val list: List<CategoriesDTO>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    inner class CategoryHolder(val binding: SingleCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding =
            SingleCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item = list[position]

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