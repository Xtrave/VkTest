package com.nikkazakov.vktest.di

import androidx.paging.PagingSource
import com.nikkazakov.vktest.data.Product
import com.nikkazakov.vktest.data.ProductsApi
import com.nikkazakov.vktest.data.ProductsPagingSource
import com.nikkazakov.vktest.data.RetrofitClient.retrofit

object Creator {

    private fun getProductsApi() = retrofit.create(ProductsApi::class.java)

    fun getProductsPagingSource(
        productApi: ProductsApi = getProductsApi()
    ): PagingSource<Int, Product> = ProductsPagingSource(productApi)

}