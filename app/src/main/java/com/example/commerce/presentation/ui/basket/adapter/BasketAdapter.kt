package com.example.commerce.presentation.ui.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.databinding.SingleBasketBinding
import com.example.commerce.domain.model.BasketModel
import kotlin.math.roundToInt

class BasketAdapter(val list: List<BasketModel>) :
    RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    inner class BasketHolder(val binding: SingleBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val binding =
            SingleBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val basket = list[position]

        with(holder) {
            binding.titleTxt.text = basket.title
            binding.quantityTxt.text = "- ${basket.quantity}"
            binding.priceTxt.text = "$ ${basket.price.roundToInt() * basket.quantity}"
            binding.productImage.setImageURL(basket.image, binding.root.context)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(basket)
            }
        }
    }
}

private var onItemClickListener: ((BasketModel) -> Unit)? = null

fun setOnItemClickListener(listener: (BasketModel) -> Unit) {
    onItemClickListener = listener
}