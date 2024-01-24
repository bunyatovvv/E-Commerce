package com.example.commerce.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CategoriesDTO
import com.example.commerce.databinding.CategoiresRowBinding

class CategoiresAdapter(val list: List<CategoriesDTO>) :
    RecyclerView.Adapter<CategoiresAdapter.CategoriesHolder>() {

    inner class CategoriesHolder(val binding: CategoiresRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val binding =
            CategoiresRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.ctgTitle.text = item.name
            binding.ctgThumbnail.setImageURL(item.thumbnail, holder.binding.root.context)
        }
    }
}