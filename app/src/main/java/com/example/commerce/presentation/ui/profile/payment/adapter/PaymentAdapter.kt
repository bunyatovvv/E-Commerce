package com.example.commerce.presentation.ui.profile.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.databinding.SingleAddressBinding
import com.example.commerce.databinding.SinglePaymentBinding
import com.example.commerce.presentation.ui.profile.address.adapter.AddressAdapter

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PaymentHolder>() {

    inner class PaymentHolder(val binding: SinglePaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<PaymentDTO>() {
        override fun areItemsTheSame(oldItem: PaymentDTO, newItem: PaymentDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PaymentDTO, newItem: PaymentDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    var list: List<PaymentDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val binding = SinglePaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val payment = list[position]

        holder.binding.cardNumber.text = payment.number.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(payment)
            }
        }
    }

    private var onItemClickListener: ((PaymentDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (PaymentDTO) -> Unit) {
        onItemClickListener = listener
    }
}