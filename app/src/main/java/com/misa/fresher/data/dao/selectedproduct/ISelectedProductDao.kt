package com.misa.fresher.data.dao.selectedproduct

import com.misa.fresher.data.model.SelectedProducts

interface ISelectedProductDao {
    suspend fun addSelectedProduct(selectedProducts: SelectedProducts, billId: Int): Long
    suspend fun getSelectedProductByBill(billId: Int): MutableList<SelectedProducts>
}