package com.misa.fresher.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class ItemBillDetail() : Parcelable {

    var id = ""
    var name: String=""
    var code: String =""
    var itemProduct:ItemProduct?=null
    var idBill:String=""
    var quantity:Int = 0


    constructor(itemProduct: ItemProduct?, idBill:String,  quantity: Int) : this() {
        this.name = itemProduct?.name + "(" + itemProduct?.color?.lowercase() + ")"
        this.code = itemProduct?.code + "-" + itemProduct?.color?.lowercase()
        this.id = UUID.randomUUID().toString()
        this.itemProduct = itemProduct
        this.idBill = idBill
        this.quantity = quantity
    }

    @SuppressLint("Range")
    constructor(cursor:Cursor) : this() {
        ItemBillDetail(
            ItemProduct(
                cursor.getString(cursor.getColumnIndex(ItemProduct.NAME)),
                cursor.getFloat(cursor.getColumnIndex(ItemProduct.PRICE)),
                cursor.getString(cursor.getColumnIndex(ItemProduct.CODE)),
                cursor.getString(cursor.getColumnIndex(ItemProduct.COLOR)),
                cursor.getString(cursor.getColumnIndex(ItemProduct.CATEGORY)),
                cursor.getInt(cursor.getColumnIndex(ItemProduct.AVAILABLE_QUANTITY)),
                cursor.getString(cursor.getColumnIndex(ItemProduct.DATE_ARRIVAL))
            ),
            cursor.getString(cursor.getColumnIndex(ItemBillDetail.ID_ITEM_BILL)),
            cursor.getInt(cursor.getColumnIndex(ItemBillDetail.QUANTITY))
        )
    }

    fun updateQuantity(num:Int)
    {
        this.quantity +=num
    }

    fun getAllPrice():Float = quantity * itemProduct!!.price

    fun getContentValues(): ContentValues {
        return ContentValues().apply {
            put(ID,id)
            put(NAME,name)
            put(CODE,code)
            put(ID_ITEM_PRODUCT,itemProduct!!.id)
            put(ID_ITEM_BILL,idBill)
            put(QUANTITY,quantity)
        }
    }

    companion object {
        const val TABLE_NAME: String = "ITEM_BILL_DETAIL_TABLE"
        const val ID: String = "id"
        const val NAME: String = "name"
        const val CODE: String = "code"
        const val ID_ITEM_PRODUCT: String = "idItemProduct"
        const val ID_ITEM_BILL: String = "idItemBill"
        const val QUANTITY:String = "quantity"
    }
}

