package com.nikkazakov.vktest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikkazakov.vktest.retrofit.Product
import com.nikkazakov.vktest.retrofit.Products
import com.nikkazakov.vktest.retrofit.ProductsApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Suppress("NAME_SHADOWING")
class Fragment1 : Fragment() {

    private lateinit var adapter: Adapter
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_1, container, false)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productApi = retrofit.create(ProductsApi::class.java)

        val  rvProductList = view.findViewById<RecyclerView>(R.id.rv_products_list)
        adapter = Adapter()
        rvProductList.adapter = adapter

//        val product: Deferred<Products> = lifecycleScope.async(Dispatchers.IO) {
//            productApi.getProduct()
//        }
//        lifecycleScope.launch(Dispatchers.Main) {
//            adapter.productsList =  product.await().products
//        }

        lifecycleScope.launch(Dispatchers.IO) {
            val product = productApi.getProduct()
            withContext(Dispatchers.Main){
                adapter.productsList = product.products
            }
        }

        return view
    }
}