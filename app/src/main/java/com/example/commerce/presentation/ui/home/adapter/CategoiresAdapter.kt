package com.example.commerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.CategoiresRowBinding

class CategoiresAdapter :
    RecyclerView.Adapter<CategoiresAdapter.CategoriesHolder>() {

    inner class CategoriesHolder(val binding: CategoiresRowBinding) :
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val binding =
            CategoiresRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val item = category[position]
        with(holder) {
            binding.ctgTitle.text = item.name
            binding.ctgThumbnail.setImageURL(item.thumbnail, holder.binding.root.context)
        }
    }
}