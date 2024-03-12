package com.nikkazakov.vktest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.nikkazakov.vktest.data.Product

object ProductComparator : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}