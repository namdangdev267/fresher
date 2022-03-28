package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.source.IProductDataSource

class ModelUnitRepository private constructor(private val modelUnitLocalDataSource: IProductDataSource.Local.IModelUnit) :
    IProductRepository.IModelUnit {

    override fun insert(modelUnit: ModelUnit): Long {
        return modelUnitLocalDataSource.insert(modelUnit)
    }

    override fun getByModelId(modelId: Int): ArrayList<ModelUnit> {
        return modelUnitLocalDataSource.getByCol(ModelUnit.MODEL_ID, modelId.toString())
    }

    companion object {
        private var instance: ModelUnitRepository? = null
        fun getInstance(modelUnitLocalDataSource: IProductDataSource.Local.IModelUnit) =
            instance ?: ModelUnitRepository(modelUnitLocalDataSource).also { instance = it }
    }
}