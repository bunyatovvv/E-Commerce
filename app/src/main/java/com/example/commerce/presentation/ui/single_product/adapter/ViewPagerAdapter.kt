package com.example.commerce.presentation.ui.single_product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.R
import com.example.commerce.common.util.setImageURL

class ViewPagerAdapter(
    val imageList: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val image = imageList[position]
        val imageView = holder.itemView.findViewById<ImageView>(R.id.viewPagerImage)
        imageView.setImageURL(image, holder.itemView.context)
    }
}