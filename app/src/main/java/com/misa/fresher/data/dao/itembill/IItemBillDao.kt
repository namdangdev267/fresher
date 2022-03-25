package com.misa.fresher.data.dao.itembill

import com.misa.fresher.models.ItemBill


interface IItemBillDao {
    suspend fun getAllBills():MutableList<ItemBill>
    suspend fun addBill(itemProduct: ItemBill) : Long
    suspend fun getLatestIdItemBill():Int
}