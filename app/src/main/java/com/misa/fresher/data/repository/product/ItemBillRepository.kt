package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ItemBill
import com.misa.fresher.data.source.IProductDataSource


class ItemBillRepository private constructor(private val itemBillLocalDataSource: IProductDataSource.Local.IItemBill) :
    IProductRepository.IItemBill {

    override fun insert(itemBill: ItemBill): Long {
        return itemBillLocalDataSource.insert(itemBill)
    }

    override fun getByBillId(billId: Int): ArrayList<ItemBill> {
        return itemBillLocalDataSource.getByCol(ItemBill.BILL_ID, billId.toString())
    }

    companion object {
        private var instance: ItemBillRepository? = null
        fun getInstance(itemBillLocalDataSource: IProductDataSource.Local.IItemBill) =
            instance ?: ItemBillRepository(itemBillLocalDataSource).also { instance = it }
    }
}