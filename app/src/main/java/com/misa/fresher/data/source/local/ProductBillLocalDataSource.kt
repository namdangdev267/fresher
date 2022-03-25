package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ProductBillDao


class ProductBillLocalDataSource private constructor(private val productBillDao: ProductBillDao) :
    IProductDataSource.Local.IBill {
    override fun getByCol(colName: String?, colValue: String?): ArrayList<ProductBill> {
        return productBillDao.getByCol(colName, colValue)
    }

    override fun insert(row: ProductBill): Long {
        return productBillDao.insert(row)
    }

    override fun update(row: ProductBill): Long {
        return productBillDao.update(row)
    }

    override fun delete(row: ProductBill): Long {
        return productBillDao.delete(row)
    }

    companion object {
        private var instance: ProductBillLocalDataSource? = null
        fun getInstance(productBillDao: ProductBillDao) =
            instance ?: ProductBillLocalDataSource(productBillDao).also { instance = it }
    }
}
