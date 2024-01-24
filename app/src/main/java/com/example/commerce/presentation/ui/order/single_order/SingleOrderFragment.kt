package com.example.commerce.presentation.ui.order.single_order

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.commerce.R
import com.example.commerce.common.BaseFragment
import com.example.commerce.databinding.FragmentSingleOrderBinding

class SingleOrderFragment : BaseFragment<FragmentSingleOrderBinding>(FragmentSingleOrderBinding::inflate) {

    private val args : SingleOrderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val order = args.order
        binding.title.text = "Order #${order.id}"
        binding.itemSizeTxt.text = "${order.products.size} items"
        binding.addressTxt.text = order.address

        changeColorCardView(order.confirmed,binding.confirmedCheck,binding.confirmedTxt)
        changeColorCardView(order.delivered,binding.deliveredCheck,binding.deliveredTxt)
        changeColorCardView(order.placed,binding.placedCheck,binding.placedTxt)
        changeColorCardView(order.shipped,binding.shippedCheck,binding.shippedTxt)

        binding.backCardview.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.viewAlltxt.setOnClickListener {
            val action = SingleOrderFragmentDirections.actionSingleOrderFragmentToOrderDetailFragment(order)
            findNavController().navigate(action)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun changeColorCardView(isChecked : Boolean, cardView : CardView, textView: TextView){

        if (isChecked){
            cardView.setCardBackgroundColor(resources.getColor(R.color.primary))
            textView.setTextColor(resources.getColor(R.color.black))
        } else {
            cardView.setCardBackgroundColor(resources.getColor(R.color.primary_50))
            textView.setTextColor(resources.getColor(R.color.black_50))
        }

    }
}