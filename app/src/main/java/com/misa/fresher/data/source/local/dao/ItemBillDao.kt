package com.misa.fresher.data.source.local.dao

import android.database.Cursor
import com.misa.fresher.data.model.product.ItemBill
import com.misa.fresher.data.source.local.database.AppDbHelper

class ItemBillDao private constructor(dbHelper: AppDbHelper) : BaseDao<ItemBill>(dbHelper),
    IProductDao.IItemBill<ItemBill> {
    override val tableName: String = ItemBill.TABLE_NAME
    override fun fromCursor(cursor: Cursor): ItemBill? = ItemBill.fromCursor(cursor)

    override fun update(row: ItemBill): Long {
        TODO("Not yet implemented")
    }

    override fun delete(row: ItemBill): Long {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: ItemBillDao? = null
        fun getInstance(dbHelper: AppDbHelper) = instance ?: ItemBillDao(dbHelper).also { instance = it }
    }
}