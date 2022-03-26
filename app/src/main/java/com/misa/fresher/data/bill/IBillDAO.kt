package com.misa.fresher.data.bill

import com.misa.fresher.model.BillInfor

interface IBillDAO {
    suspend fun addBill(total: Int):Boolean
    suspend fun selectAllBill():ArrayList<BillInfor>
    suspend fun getBillID(): Int
    suspend fun updateBill(total:Int,billId:Int):Boolean
    suspend fun getToTalByBillID(billID: Int):Int
    suspend fun selectProductByBiLLId(billId: Int)
}