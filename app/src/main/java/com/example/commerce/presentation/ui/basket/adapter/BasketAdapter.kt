package com.example.commerce.presentation.ui.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.databinding.SingleBasketBinding
import com.example.commerce.domain.model.BasketModel
import kotlin.math.roundToInt

class BasketAdapter :
    RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    inner class BasketHolder(val binding: SingleBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object :DiffUtil.ItemCallback<BasketModel>(){
        override fun areItemsTheSame(oldItem: BasketModel, newItem: BasketModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BasketModel, newItem: BasketModel): Boolean {
            return oldItem.products_id == newItem.products_id
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
    var basket: List<BasketModel>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val binding =
            SingleBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketHolder(binding)
    }

    override fun getItemCount(): Int {
        return basket.size
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val basket = basket[position]

        with(holder.binding) {
            titleTxt.text = basket.title
            quantityTxt.text = "- ${basket.quantity}"
            priceTxt.text = "$ ${basket.price.roundToInt() * basket.quantity}"
            productImage.setImageURL(basket.image, root.context)
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