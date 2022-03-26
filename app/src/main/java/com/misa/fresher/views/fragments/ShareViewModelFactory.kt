package com.misa.fresher.views.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misa.fresher.data.repositories.BillRepository

class ShareViewModelFactory(private val billRepository: BillRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(billRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}