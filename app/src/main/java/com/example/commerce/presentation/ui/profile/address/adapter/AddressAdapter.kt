package com.example.commerce.presentation.ui.profile.address.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.OrderDTO
import com.example.commerce.databinding.SingleAddressBinding
import com.example.commerce.databinding.SingleOrderBinding
import com.example.commerce.domain.model.BasketModel
import com.example.commerce.presentation.ui.order.adapter.OrdersAdapter

class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressHolder>() {

    inner class AddressHolder(val binding: SingleAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<AddressDTO>() {
        override fun areItemsTheSame(oldItem: AddressDTO, newItem: AddressDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AddressDTO, newItem: AddressDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    var list: List<AddressDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val binding = SingleAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        val address = list[position]

        with(holder.binding) {
            addressTxt.text = "${address.zip} ${address.street_address}, ${address.state} ${address.city}"
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(address)
            }
        }
    }

    private var onItemClickListener: ((AddressDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (AddressDTO) -> Unit) {
        onItemClickListener = listener
    }
}