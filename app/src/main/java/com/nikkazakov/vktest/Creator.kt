package com.nikkazakov.vktest

import androidx.paging.PagingSource
import com.nikkazakov.vktest.RetrofitClient.retrofit
import com.nikkazakov.vktest.retrofit.Product
import com.nikkazakov.vktest.retrofit.ProductsApi

object Creator {

    private fun getProductsApi() = retrofit.create(ProductsApi::class.java)

    fun getProductsPagingSource(
        productApi: ProductsApi = getProductsApi()
    ): PagingSource<Int, Product> = ProductsPagingSource(productApi)

}