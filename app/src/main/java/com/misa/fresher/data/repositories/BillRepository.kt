package com.misa.fresher.data.repositories

import com.misa.fresher.data.dao.itembill.ItemBillDao
import com.misa.fresher.data.dao.itemproduct.ItemProductDao
import com.misa.fresher.models.ItemBill
import com.misa.fresher.models.ItemProduct

class BillRepository(private val itemBillDao: ItemBillDao) {
    suspend fun getBills():MutableList<ItemBill>
    {
        return itemBillDao.getAllBills()
    }

    suspend fun addBill(itemBill: ItemBill) {
        itemBillDao.addBill(itemBill)
    }
}