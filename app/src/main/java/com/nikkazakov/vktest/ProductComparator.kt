package com.nikkazakov.vktest

import androidx.recyclerview.widget.DiffUtil
import com.nikkazakov.vktest.retrofit.Product

object ProductComparator : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}