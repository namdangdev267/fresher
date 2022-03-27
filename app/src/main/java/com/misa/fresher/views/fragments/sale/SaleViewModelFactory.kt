package com.misa.fresher.views.fragments.sale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misa.fresher.data.repositories.ProductRepository

class SaleViewModelFactory(private val productRepository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaleViewModel::class.java)) {
            return SaleViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
