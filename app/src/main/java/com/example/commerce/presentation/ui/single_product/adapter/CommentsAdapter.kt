package com.example.commerce.presentation.ui.single_product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.commerce.R
import com.example.commerce.common.util.setImageURL
import com.example.commerce.data.dto.CommentsDTO
import com.example.commerce.data.dto.UserDTO

class CommentsAdapter(
    val commentList: List<CommentsDTO>,
    val userList: List<UserDTO>
) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.single_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        val commentDes = holder.itemView.findViewById<TextView>(R.id.commentTxt)
        val ratingBar = holder.itemView.findViewById<RatingBar>(R.id.ratingBar)
        val name = holder.itemView.findViewById<TextView>(R.id.nameSurnameText)
        val pp = holder.itemView.findViewById<ImageView>(R.id.pp)

        for (user in userList) {
            if (comment.userId == user.id) {
                name.text = "${user.name} ${user.surname}"
                pp.setImageURL(user.photo, holder.itemView.context)
            }
        }
        commentDes.text = comment.description
        ratingBar.rating = comment.rating.toFloat()
    }
}