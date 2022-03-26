package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.models.ItemBill

interface IItemBillDao {
    suspend fun getAllListBill(): MutableList<ItemBill>
    suspend fun addListBill(product: ItemBill): Long
    suspend fun getBillID(): Int
}