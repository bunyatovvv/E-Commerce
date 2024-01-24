package com.example.commerce.presentation.ui.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.data.dto.ProductDTO
import com.example.commerce.databinding.SingleOrderBinding

class OrdersAdapter(val list: List<OrderDTO>) : RecyclerView.Adapter<OrdersAdapter.OrdersHolder>() {

    inner class OrdersHolder(val binding: SingleOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersHolder {
        val binding = SingleOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OrdersHolder, position: Int) {
        val order = list[position]

        with(holder.binding) {
            orderName.text = "Order #${order.id}"
            orderItemSize.text = "${order.products.size} orders"
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(order)
            }
        }
    }

    private var onItemClickListener: ((OrderDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (OrderDTO) -> Unit) {
        onItemClickListener = listener
    }
}