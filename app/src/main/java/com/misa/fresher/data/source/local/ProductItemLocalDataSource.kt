package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ProductItemDao

class ProductItemLocalDataSource private constructor(private val  productItemDao: ProductItemDao)
    : IProductDataSource.Local.IItem{

    override fun getByCol(colName: String?, colValue: String?): ArrayList<ProductItem> {
        return productItemDao.getByCol(colName, colValue)
    }

    override fun insert(row: ProductItem): Long {
        return productItemDao.insert(row)
    }

    override fun update(row: ProductItem): Long {
        return productItemDao.update(row)
    }

    override fun delete(row: ProductItem): Long {
        return productItemDao.delete(row)
    }

    companion object {
        private var instance: ProductItemLocalDataSource? = null
        fun getInstance(productItemDao: ProductItemDao) =
            instance ?: ProductItemLocalDataSource(productItemDao).also { instance = it }
    }

}