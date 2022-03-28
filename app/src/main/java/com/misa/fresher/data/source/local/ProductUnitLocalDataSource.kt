package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ProductUnitDao

class ProductUnitLocalDataSource private constructor(private val productUnitDao: ProductUnitDao) :
    IProductDataSource.Local.IUnit {

    override fun getByCol(colName: String?, colValue: String?): ArrayList<ProductUnit> {
        return productUnitDao.getByCol(colName, colValue)
    }

    override fun getByModelId(modelId: Int): ArrayList<ProductUnit> {
        return productUnitDao.getByModelId(modelId)
    }

    override fun insert(row: ProductUnit): Long {
        return productUnitDao.insert(row)
    }

    override fun update(row: ProductUnit): Long {
        return productUnitDao.update(row)
    }

    override fun delete(row: ProductUnit): Long {
        return productUnitDao.delete(row)
    }

    companion object {
        private var instance: ProductUnitLocalDataSource? = null
        fun getInstance(productUnitDao: ProductUnitDao) =
            instance ?: ProductUnitLocalDataSource(productUnitDao).also { instance = it }
    }
}
