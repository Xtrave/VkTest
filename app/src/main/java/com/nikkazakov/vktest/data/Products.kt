package com.nikkazakov.vktest.data

import com.google.gson.annotations.SerializedName
import com.nikkazakov.vktest.data.Product

data class Products(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
