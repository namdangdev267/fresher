package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ProductModelDao

class ProductModelLocalDataSource private constructor(private val productModelDao: ProductModelDao) :
    IProductDataSource.Local.IModel {
    override fun getByCol(colName: String?, colValue: String?): ArrayList<ProductModel> {
        return productModelDao.getByCol(colName, colValue)
    }

    override fun insert(row: ProductModel): Long {
        return productModelDao.insert(row)
    }

    override fun update(row: ProductModel): Long {
        return productModelDao.update(row)
    }

    override fun delete(row: ProductModel): Long {
        return productModelDao.delete(row)
    }

    companion object {
        private var instance: ProductModelLocalDataSource? = null
        fun getInstance(productModelDao: ProductModelDao) =
            instance ?: ProductModelLocalDataSource(productModelDao).also { instance = it }
    }

}