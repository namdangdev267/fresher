package com.misa.fresher.data.dao.selectedproduct

import com.misa.fresher.data.model.SelectedProducts

interface ISelectedProductDao {
    fun addSelectedProduct(selectedProducts: SelectedProducts,billId: Int) : Long
    fun getSelectedProDuctByBill(billId:Int) : MutableList<SelectedProducts>
}