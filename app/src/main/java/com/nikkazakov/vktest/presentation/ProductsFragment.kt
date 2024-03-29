package com.nikkazakov.vktest.presentation

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
import com.nikkazakov.vktest.R
import com.nikkazakov.vktest.di.Creator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@Suppress("NAME_SHADOWING")
class ProductsFragment : Fragment() {

    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter(ProductComparator)
    }

    private val flow = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            initialLoadSize = 20
        )
    ) {
        Creator.getProductsPagingSource()
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
            flow.collectLatest { products ->
                adapter.submitData(products)
            }
        }
        return view
    }
}