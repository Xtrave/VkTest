package com.nikkazakov.vktest.retrofit

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    val products: List<Product>
)
