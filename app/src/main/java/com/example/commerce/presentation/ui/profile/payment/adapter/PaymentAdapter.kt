package com.example.commerce.presentation.ui.profile.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.data.dto.AddressDTO
import com.example.commerce.data.dto.PaymentDTO
import com.example.commerce.databinding.SingleAddressBinding
import com.example.commerce.databinding.SinglePaymentBinding
import com.example.commerce.presentation.ui.profile.address.adapter.AddressAdapter

class PaymentAdapter (val list: List<PaymentDTO>) : RecyclerView.Adapter<PaymentAdapter.PaymentHolder>() {

    inner class PaymentHolder(val binding: SinglePaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

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