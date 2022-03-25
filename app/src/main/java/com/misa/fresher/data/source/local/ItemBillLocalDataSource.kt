package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ItemBill
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ItemBillDao


class ItemBillLocalDataSource private constructor(private val itemBillDao: ItemBillDao) :
    IProductDataSource.Local.IItemBill {
    override fun getByCol(colName: String?, colValue: String?): ArrayList<ItemBill> {
        return itemBillDao.getByCol(colName, colValue)
    }

    override fun insert(row: ItemBill): Long {
        return itemBillDao.insert(row)
    }

    override fun update(row: ItemBill): Long {
        return itemBillDao.update(row)
    }

    override fun delete(row: ItemBill): Long {
        return itemBillDao.delete(row)
    }

    companion object {
        private var instance: ItemBillLocalDataSource? = null
        fun getInstance(itemBillDao: ItemBillDao) =
            instance ?: ItemBillLocalDataSource(itemBillDao).also { instance = it }
    }
}
