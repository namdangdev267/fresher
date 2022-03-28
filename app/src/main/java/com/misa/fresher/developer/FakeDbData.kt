package com.misa.fresher.developer

import android.content.Context
import android.util.Log
import com.misa.fresher.R
import com.misa.fresher.data.model.product.ModelUnit
import com.misa.fresher.data.model.product.ProductItem
import com.misa.fresher.data.model.product.ProductModel
import com.misa.fresher.data.model.product.ProductUnit
import com.misa.fresher.data.source.local.database.AppDbHelper
import com.misa.fresher.di.Injector
import com.misa.fresher.utils.Queries

object FakeDbData {

    fun createDB(context: Context) {
//        context.deleteDatabase(Queries.DATABASE_NAME)
        fakeData(context)
//        openAndKeepDB(context)
    }

    //    private fun openAndKeepDB(context: Context) {
//        val dbHelper = AppDbHelper.getInstance(context)
//        val db = dbHelper.readableDatabase
//    }
//
    private fun fakeData(context: Context) {
        /**
         * - purpose: insert fake unit data to PRODUCT_UNIT table
         *
         * @author HTLong
         * @edit_at 25/03/2022
         */

        val modelRepo = Injector.getProductModelRepository(context)
        if(modelRepo.getAll().isNotEmpty()) return

        val itemRepo = Injector.getProductItemRepository(context)
        val modelUnitRepo = Injector.getModelUnitRepository(context)
        val unitRepo = Injector.getProductUnitRepository(context)

        context.deleteDatabase(Queries.DATABASE_NAME)

        val unitIDs = arrayListOf<Long>()
        arrayListOf(
            ProductUnit(name = "Piece", value = 1),
            ProductUnit(name = "Pair", value = 2),
            ProductUnit(name = "Set", value = 3),
            ProductUnit(name = "Dozen", value = 12)
        ).forEach {
            val res = unitRepo.insert(it)
            unitIDs.add(res)
            Log.i("--FAKE DB DATA--", "---INSERT PRODUCT UNIT--- name = ${it.name}, ID = $res")
        }


        /**
         * - purpose: insert fake model data to PRODUCT_MODEL table
         *
         * @author HTLong
         * @edit_at 25/03/2022
         */

        val image = R.drawable.ic_product
        for (i in 5 downTo 1) {
            val pModel = ProductModel(
                name = "name $i",
                code = "code$i",
                category = "type${i / 2}",
                date = i * 1000L,
                image = image
            )
            val pModelId = modelRepo.insert(pModel).toInt()
            Log.i("--FAKE DB DATA--", "---INSERT PRODUCT MODEL--- name = ${pModel.name}, ID = $pModelId")

            /**
             * - purpose: insert fake item data to PRODUCT_ITEM table
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            arrayListOf(
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-S-RED",
                    name = "${pModel.name} (s/red)",
                    size = "S",
                    color = "red",
                    price = 1000.0,
                    quantity = 10
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-L-BLACK",
                    name = "${pModel.name} (l/black)",
                    size = "L",
                    color = "black",
                    price = 3000.0,
                    quantity = 10
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-M-RED",
                    name = "${pModel.name} (m/red)",
                    size = "M",
                    color = "red",
                    price = 1500.0,
                    quantity = 10
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-M-BLACK",
                    name = "${pModel.name} (m/black)",
                    size = "M",
                    color = "black",
                    price = 2700.0,
                    quantity = 10
                ),
            ).forEach {
                val pItemId = itemRepo.insert(it)
                Log.i("--FAKE DB DATA--", "---INSERT PRODUCT ITEM--- name = ${it.name}, ID = $pItemId")
            }

            /**
             * - purpose: insert fake model_unit data to MODEL_UNIT table
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            unitIDs.forEach { unitId ->
                val pItemId = modelUnitRepo.insert(ModelUnit(modelId = pModelId, unitId = unitId.toInt()))
                Log.i(
                    "--FAKE DB DATA--",
                    "---INSERT MODEL_UNIT --- modelId = $pModelId, unitId = $unitId, ID = $pItemId"
                )
            }
        }

        /**
         * - purpose: insert fake model data to PRODUCT_MODEL table
         *
         * @author HTLong
         * @edit_at 25/03/2022
         */

        for (i in 15 downTo 11) {
            val pModel = ProductModel(
                name = "name $i",
                code = "code$i",
                category = "type${i / 4}",
                date = i * 1000L,
                image = image
            )
            val pModelId = modelRepo.insert(pModel).toInt()
            Log.i("--FAKE DB DATA--", "---INSERT PRODUCT MODEL--- name = ${pModel.name}, ID = $pModelId")

            /**
             * - purpose: insert fake item data to PRODUCT_ITEM table
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            arrayListOf(
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-XXL-BLUE",
                    name = "${pModel.name} (xxl/blue)",
                    size = "XXL",
                    color = "blue",
                    price = 1000.0,
                    quantity = 0
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-L-BLUE",
                    name = "${pModel.name} (l/blue)",
                    size = "L",
                    color = "blue",
                    price = 3200.0,
                    quantity = 12
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-S-BLACK",
                    name = "${pModel.name} (s/black)",
                    size = "S",
                    color = "black",
                    price = 1600.0,
                    quantity = 0
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-M-RED",
                    name = "${pModel.name} (m/red)",
                    size = "M",
                    color = "red",
                    price = 2600.0,
                    quantity = 3
                ),
            ).forEach {
                val pItemId = itemRepo.insert(it)
                Log.i("--FAKE DB DATA--", "---INSERT PRODUCT ITEM--- name = ${it.name}, ID = $pItemId")
            }

            /**
             * - purpose: insert fake model_unit data to MODEL_UNIT table
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            unitIDs.forEach { unitId ->
                val pItemId = modelUnitRepo.insert(ModelUnit(modelId = pModelId, unitId = unitId.toInt()))
                Log.i(
                    "--FAKE DB DATA--",
                    "---INSERT MODEL_UNIT --- modelId = $pModelId, unitId = $unitId, ID = $pItemId"
                )
            }
        }
        for (i in 10 downTo 6) {
            val pModel = ProductModel(
                name = "name $i",
                code = "code$i",
                category = "type${i / 3}",
                date = i * 1000L,
                image = image
            )
            val pModelId = modelRepo.insert(pModel).toInt()

            arrayListOf(
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-XL-BLACK",
                    name = "${pModel.name} (xl/black)",
                    size = "XL",
                    color = "black",
                    price = 1000.0,
                    quantity = 0
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-M-BLUE",
                    name = "${pModel.name} (m/blue)",
                    size = "M",
                    color = "blue",
                    price = 3200.0,
                    quantity = 0
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-S-BLACK",
                    name = "${pModel.name} (s/black)",
                    size = "S",
                    color = "black",
                    price = 1300.0,
                    quantity = 0
                ),
                ProductItem(
                    modelId = pModelId,
                    code = "${pModel.code}-S-RED",
                    name = "${pModel.name} (s/red)",
                    size = "S",
                    color = "red",
                    price = 2100.0,
                    quantity = 0
                ),
            ).forEach {
                val pItemId = itemRepo.insert(it)
                Log.i("--FAKE DB DATA--", "---INSERT PRODUCT ITEM--- name = ${it.name}, ID = $pItemId")
            }


            /**
             * - purpose: insert fake model_unit data to MODEL_UNIT table
             *
             * @author HTLong
             * @edit_at 25/03/2022
             */
            unitIDs.forEach { unitId ->
                val pItemId = modelUnitRepo.insert(ModelUnit(modelId = pModelId, unitId = unitId.toInt()))
                Log.i(
                    "--FAKE DB DATA--",
                    "---INSERT MODEL_UNIT --- modelId = $pModelId, unitId = $unitId, ID = $pItemId"
                )
            }
        }
    }
}