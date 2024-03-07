package com.nikkazakov.vktest.retrofit

import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProduct(): Products
}