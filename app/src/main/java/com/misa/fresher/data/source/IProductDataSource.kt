package com.misa.fresher.data.source

import com.misa.fresher.data.model.product.ProductBill
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.model.product.ItemBill

interface IProductDataSource {
    interface Local {
        interface IBase<T> {
            fun getByCol(colName: String? = null, colValue: String? = null): ArrayList<T>
            fun insert(row: T): Long
            fun update(row: T): Long
            fun delete(row: T): Long
        }

        interface IModel: IBase<ProductModel> {

        }

        interface IItem: IBase<ProductItem> {
        }

        interface IUnit: IBase<ProductUnit> {
            fun getByModelId(modelId: Int): ArrayList<ProductUnit>
        }

        interface IBill: IBase<ProductBill> {

        }

        interface IModelUnit: IBase<ModelUnit> {

        }

        interface IItemBill: IBase<ItemBill> {

        }
    }

    interface Remote {

    }
}