package com.example.commerce.presentation.ui.order_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.common.util.setImageURL
import com.example.commerce.databinding.SingleOrderDetailBinding
import com.example.commerce.domain.model.BasketModel
import kotlin.math.roundToInt

class OrderDetailAdapter(val list: List<BasketModel>) :
    RecyclerView.Adapter<OrderDetailAdapter.OrderDetailHolder>() {

    inner class OrderDetailHolder(val binding: SingleOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailHolder {
        val binding =
            SingleOrderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderDetailHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OrderDetailHolder, position: Int) {
        val order = list[position]

        with(holder.binding) {
            priceTxt.text = "$ ${order.price.roundToInt() * order.quantity}"
            quantityTxt.text = order.quantity.toString()
            titleTxt.text = order.title
            productImage.setImageURL(order.image, this.root.context)
        }
    }
}