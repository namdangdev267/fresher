package com.misa.fresher.data.dao.itembilldetail

import com.misa.fresher.models.ItemBillDetail

interface IItemBillDetailDao {
    suspend fun getAllBillDetails():MutableList<ItemBillDetail>
    suspend fun addBillDetail(itemBillDetail: ItemBillDetail) : Long
    suspend fun getItemBillDetailWithBillId(billId:String):MutableList<ItemBillDetail>
}