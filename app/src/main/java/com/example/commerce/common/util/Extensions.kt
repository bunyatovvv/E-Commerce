package com.example.commerce.common.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.commerce.R

fun ImageView.setImageURL(url: String, context: Context) {
    val options = RequestOptions.placeholderOf(placeHolder(context))
        .error(R.drawable.ic_launcher_foreground)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

private fun placeHolder(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 8f
    circularProgressDrawable.centerRadius = 40f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun RecyclerView.LayoutManager.linear(context: Context) {
    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.vertical(context: Context) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}