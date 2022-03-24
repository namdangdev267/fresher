package com.misa.fresher.data.dao.bill

import com.misa.fresher.data.model.Bill

interface IBillDao {
    suspend fun addBill(bill: Bill) : Long
    suspend fun getAllBill() : MutableList<Bill>
    suspend fun getLastestBillID() : Int
}