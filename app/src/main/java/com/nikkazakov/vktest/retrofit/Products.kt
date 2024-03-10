package com.nikkazakov.vktest.retrofit

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
