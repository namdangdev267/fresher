package com.misa.fresher.views.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misa.fresher.data.repositories.BillRepository
import com.misa.fresher.data.repositories.InfoShipRepository

class ShareViewModelFactory(private val billRepository: BillRepository,private val infoShipRepository: InfoShipRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(billRepository,infoShipRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}