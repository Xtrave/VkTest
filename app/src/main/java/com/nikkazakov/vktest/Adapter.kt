package com.nikkazakov.vktest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.nikkazakov.vktest.retrofit.Product

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var productsList = listOf<Product>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvtitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvdescription = view.findViewById<TextView>(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = productsList[position]
        Glide.with(holder.imageView.context)
            .load(productItem.imageUrl)
            .into(holder.imageView)
        holder.tvtitle.text = productItem.title
        holder.tvdescription.text = productItem.description

    }
}