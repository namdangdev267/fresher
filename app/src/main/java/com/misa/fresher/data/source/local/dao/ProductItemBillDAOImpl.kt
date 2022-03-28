package com.misa.fresher.data.source.local.dao

import com.misa.fresher.data.entity.ProductItemBill
import com.misa.fresher.data.source.local.database.AppDatabase
import com.misa.fresher.util.query

/**
 * @version 1
 * @updated 3/25/2022: Override lần đầu
 */
class ProductItemBillDAOImpl(
    private val appDatabase: AppDatabase
) : ProductItemBillDAO {

    private val itemDAO = ProductItemDAO.getInstance(appDatabase)

    /**
     * @version 1
     * @updated 3/25/2022: Override lần đầu
     */
    override fun getByBillId(billId: Long): List<ProductItemBill> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(ProductItemBill.TABLE_NAME)
            .setSelection("${ProductItemBill.BILL_ID} = ?")
            .setSelectionArgs(arrayOf(billId.toString()))
            .call()
        val list = mutableListOf<ProductItemBill>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(ProductItemBill(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        val result = mutableListOf<ProductItemBill>()
        list.forEach { itemBill ->
            itemDAO.getById(itemBill.item.id)?.let {
                result.add(ProductItemBill(itemBill.id, it, itemBill.quantity))
            }
        }

        return result
    }
}