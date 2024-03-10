package com.nikkazakov.vktest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.RecyclerView
import com.nikkazakov.vktest.retrofit.ProductsApi
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("NAME_SHADOWING")
class ProductsFragment : Fragment() {

    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter(ProductComparator)
    }

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com").client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val productApi = retrofit.create(ProductsApi::class.java)

    private val flow = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            initialLoadSize = 20
        )
    ) {
        ProductsPagingSource(productApi)
    }.flow

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_products, container, false)

        val rvProductList = view.findViewById<RecyclerView>(R.id.rv_products_list)
        rvProductList.adapter = adapter

        lifecycleScope.launch {
            flow.collectLatest { products->
                adapter.submitData(products)
            }
        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            val product = productApi.getProduct()
//            withContext(Dispatchers.Main) {
//                adapter.productsList = product.products
//            }
//        }

        return view
    }
}