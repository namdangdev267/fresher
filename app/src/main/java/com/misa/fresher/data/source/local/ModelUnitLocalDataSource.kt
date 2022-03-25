package com.misa.fresher.data.source.local

import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.source.IProductDataSource
import com.misa.fresher.data.source.local.dao.ModelUnitDao


class ModelUnitLocalDataSource private constructor(private val modelUnitDao: ModelUnitDao) :
    IProductDataSource.Local.IModelUnit {
    override fun getByCol(colName: String?, colValue: String?): ArrayList<ModelUnit> {
        return modelUnitDao.getByCol(colName, colValue)
    }

    override fun insert(row: ModelUnit): Long {
        return modelUnitDao.insert(row)
    }

    override fun update(row: ModelUnit): Long {
        return modelUnitDao.update(row)
    }

    override fun delete(row: ModelUnit): Long {
        return modelUnitDao.delete(row)
    }

    companion object {
        private var instance: ModelUnitLocalDataSource? = null
        fun getInstance(modelUnitDao: ModelUnitDao) =
            instance ?: ModelUnitLocalDataSource(modelUnitDao).also { instance = it }
    }
}
