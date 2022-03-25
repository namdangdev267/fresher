package com.misa.fresher.data.dao.infoship

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import com.misa.fresher.data.dao.itemproduct.ItemProductDao
import com.misa.fresher.data.database.AppDatabase
import com.misa.fresher.models.InfoShip
import com.misa.fresher.models.ItemProduct

class InfoShipDao(private val appDatabase: AppDatabase):IInfoShipDao {

    companion object {
        private var instance: InfoShipDao? = null
        fun getInstance(appDatabase: AppDatabase): InfoShipDao =
            instance ?: InfoShipDao.getInstance(appDatabase).also { instance = it }
    }

    @SuppressLint("Range")
    override suspend fun getInforShipWithID(id: Int): InfoShip? {
        val db = appDatabase.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${InfoShip.TABLE_NAME} WHERE ${InfoShip.ID} = $id",
            null
        )

        var infoShip: InfoShip? = null
        if (cursor.moveToFirst()) {
            infoShip = InfoShip(
                cursor
            )
        }
        cursor.close()
        db.close()
        return infoShip
    }

    override suspend fun addInfoShip(infoShip: InfoShip): Long {
        val db = appDatabase.writableDatabase
        return db.insertWithOnConflict(
            InfoShip.TABLE_NAME, null,
            infoShip.getContentValues(), SQLiteDatabase.CONFLICT_REPLACE
        )
    }


    override suspend fun getAllInfoShips(): MutableList<InfoShip> {
        val db = appDatabase.readableDatabase
        val cursor = db.query(
            InfoShip.TABLE_NAME, null, null,
            null, null, null, null
        )

        val list = mutableListOf<InfoShip>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                list.add(
                    InfoShip(
                        cursor
                    )
                )
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return list
    }




}