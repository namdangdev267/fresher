package com.misa.fresher.data.repository.product

import com.misa.fresher.data.model.product.*

interface IProductRepository {
    interface IModel {
        fun insert(productModel: ProductModel): Long
        fun getAll(): ArrayList<ProductModel>
        fun getById(modelId: Int): ProductModel?
    }

    interface IItem {
        fun insert(productItem: ProductItem): Long
        fun getByModelId(modelId: Int): ArrayList<ProductItem>
        fun getById(itemId: Int): ProductItem?
    }

    interface IUnit {
        fun insert(productUnit: ProductUnit): Long
        fun getByModelId(modelId: Int): ArrayList<ProductUnit>
        fun getById(unitId: Int): ProductUnit?
    }

    interface IBill {
        fun insert(productBill: ProductBill): Long
        fun getAll(): ArrayList<ProductBill>
    }

    interface IModelUnit {
        fun insert(modelUnit: ModelUnit): Long
        fun getByModelId(modelId: Int): ArrayList<ModelUnit>
    }

    interface IItemBill {
        fun insert(itemBill: ItemBill): Long
        fun getByBillId(billId: Int): ArrayList<ItemBill>
    }
}

