package com.misa.fresher.data.cart

import com.misa.fresher.model.BillInfor
import com.misa.fresher.model.Product

interface ICartDAO {
    suspend fun addItem(billID:Int,product:Product,quantity:Int):Boolean
}