package com.nikkazakov.vktest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikkazakov.vktest.retrofit.Product

class ProductsAdapter(
    diffCallback: DiffUtil.ItemCallback<Product>
) : PagingDataAdapter<Product, ProductsAdapter.ViewHolder>(diffCallback) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val tvtitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvdescription = view.findViewById<TextView>(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_product,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = getItem(position) ?: return
        Glide.with(holder.imageView.context)
            .load(productItem.imageUrl)
            .into(holder.imageView)
        holder.tvtitle.text = productItem.title
        holder.tvdescription.text = productItem.description

    }
}