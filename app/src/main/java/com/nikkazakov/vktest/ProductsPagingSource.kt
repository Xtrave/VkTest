package com.nikkazakov.vktest

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikkazakov.vktest.retrofit.Product
import com.nikkazakov.vktest.retrofit.ProductsApi

class ProductsPagingSource(
    val productsApi: ProductsApi
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentKey = params.key ?: 0
            val response = productsApi.getProduct(skip = currentKey, limit = params.loadSize)

            val nextKey =
                if (response.products.isEmpty() || response.products.size < params.loadSize) {
                    null
                } else {
                    response.skip + response.limit
                }

            LoadResult.Page(data = response.products, prevKey = null, nextKey = nextKey)
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val pageSize = 20
    }
}
