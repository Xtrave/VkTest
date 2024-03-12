package com.nikkazakov.vktest.data

import com.nikkazakov.vktest.data.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("products")
    suspend fun getProduct(
        @Query("skip")
        skip: Int,
        @Query("limit")
        limit: Int
    ): Products
}